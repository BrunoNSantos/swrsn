package com.brunonsantos.swrsn.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.brunonsantos.swrsn.dto.AtualizarLocalizacaoDTO;
import com.brunonsantos.swrsn.dto.NegociarItensDTO;
import com.brunonsantos.swrsn.dto.RebeldeDTO;
import com.brunonsantos.swrsn.dto.ReportarTraidorDTO;
import com.brunonsantos.swrsn.enums.TipoItemInventario;
import com.brunonsantos.swrsn.exception.NegociacaoException;
import com.brunonsantos.swrsn.exception.RegistroNaoEncontradoException;
import com.brunonsantos.swrsn.model.ItemInventario;
import com.brunonsantos.swrsn.model.Rebelde;
import com.brunonsantos.swrsn.model.ReportTraidor;
import com.brunonsantos.swrsn.repository.RebeldeRepository;
import com.brunonsantos.swrsn.repository.ReportTraidorRepository;
import com.brunonsantos.swrsn.service.RebeldeService;

/**
 * @author bruno
 *
 */
@Service
public class RebeldeServiceImpl implements RebeldeService {

	@Resource
	private RebeldeRepository rebeldeRepository;

	@Resource
	private ReportTraidorRepository reportTraidorRepository;

	/**
	 * Método responsável por adicionar um novo rebelde
	 */
	@Override
	public Rebelde adicionarRebelde(RebeldeDTO rebeldeDTO) {
		Rebelde rebelde = new Rebelde();
		BeanUtils.copyProperties(rebeldeDTO, rebelde, "isTraidor");
		this.criarInventario(rebeldeDTO, rebelde);

		return this.rebeldeRepository.save(rebelde);
	}

	/**
	 * Método responsável por criar um inventario
	 */
	private void criarInventario(RebeldeDTO rebeldeDTO, Rebelde rebelde) {
		List<ItemInventario> itensInventario = new ArrayList<ItemInventario>();

		for (TipoItemInventario itemInventarioDTO : rebeldeDTO.getItensInventario()) {
			ItemInventario itemInventario = new ItemInventario();
			itemInventario.setTipo(itemInventarioDTO);
			itensInventario.add(itemInventario);
		}

		rebelde.atualizarItensInventario(itensInventario);
	}

	/**
	 * Método responsável por listar todos os rebeldes
	 */
	@Override
	public List<Rebelde> listarTodosRebeldes() {
		return this.rebeldeRepository.findAll();
	}

	/**
	 * Método responsável por atualizar a localização de um rebelde
	 */
	@Override
	public void atualizarLocalizacao(AtualizarLocalizacaoDTO params) throws RegistroNaoEncontradoException {
		Rebelde rebelde = this.encontrarRebelde(params.getIdRebelde());

		rebelde.setLatitude(params.getLatitude());
		rebelde.setLongitude(params.getLongitude());

		this.rebeldeRepository.save(rebelde);
	}

	/**
	 * Método responsável por encontrar um rebelde pelo Id
	 */
	@Override
	public Rebelde encontrarRebelde(Long id) throws RegistroNaoEncontradoException {
		Optional<Rebelde> rebeldeOptional = this.rebeldeRepository.findById(id);
		if (rebeldeOptional.isPresent()) {
			return rebeldeOptional.get();
		} else {
			throw new RegistroNaoEncontradoException("O rebelde não foi localizado");
		}
	}

	/**
	 * Método responsável por reportar um traidor
	 */
	@Override
	public void reportarTraidor(ReportarTraidorDTO params) throws RegistroNaoEncontradoException {
		final Long QTD_REPORTS_PARA_TRAIDOR = 3L;

		ReportTraidor reportTraidor = new ReportTraidor();
		reportTraidor.setRebelde(this.encontrarRebelde(params.getIdRebelde()));
		reportTraidor.setTraidor(this.encontrarRebelde(params.getIdTraidor()));
		this.reportTraidorRepository.save(reportTraidor);

		if (this.reportTraidorRepository.countByTraidorId(params.getIdTraidor()) >= QTD_REPORTS_PARA_TRAIDOR) {
			Rebelde rebeldeTraidor = this.encontrarRebelde(params.getIdTraidor());
			rebeldeTraidor.setIsTraidor(true);
			this.rebeldeRepository.save(rebeldeTraidor);
		}
	}

	/**
	 * Método responsável por negociar itens entre dois rebeldes
	 * 
	 * @throws NegociacaoException
	 */
	@Override
	public void negociarItens(NegociarItensDTO params) throws RegistroNaoEncontradoException, NegociacaoException {
		Rebelde rebelde1 = this.encontrarRebelde(params.getIdRebeldeNegociante1());
		Rebelde rebelde2 = this.encontrarRebelde(params.getIdRebeldeNegociante2());
		Long qtdPontosRebelde1 = this.calcularQtdPontosItens(params.getItensNegociante1());
		Long qtdPontosRebelde2 = this.calcularQtdPontosItens(params.getItensNegociante2());

		this.validarNegociacao(rebelde1, rebelde2, qtdPontosRebelde1, qtdPontosRebelde2);

		for (TipoItemInventario tipoItem : params.getItensNegociante1()) {
			this.removerItemInventario(rebelde1, tipoItem);
			this.adicionarItemInventario(rebelde2, tipoItem);
		}

		for (TipoItemInventario tipoItem : params.getItensNegociante2()) {
			this.removerItemInventario(rebelde2, tipoItem);
			this.adicionarItemInventario(rebelde1, tipoItem);
		}
	}

	/**
	 * Método responsável por adicionar um item no inventário de um rebelde
	 */
	private void adicionarItemInventario(Rebelde rebelde, TipoItemInventario tipoItem) {
		ItemInventario itemInventario = new ItemInventario();
		itemInventario.setTipo(tipoItem);
		rebelde.addItemInventario(itemInventario);
		this.rebeldeRepository.save(rebelde);
	}

	/**
	 * Método responsável por remover um item do inventário de um rebelde
	 * 
	 * @throws NegociacaoException
	 */
	private void removerItemInventario(Rebelde rebelde, TipoItemInventario tipoItem) throws NegociacaoException {
		ItemInventario itemInventarioRemover = null;

		for (ItemInventario itemInventario : rebelde.getItensInventario()) {
			if (itemInventario.getTipo() == tipoItem) {
				itemInventarioRemover = itemInventario;
				break;
			}
		}

		if (itemInventarioRemover != null) {
			rebelde.removerItemInventario(itemInventarioRemover);
		} else {
			throw new NegociacaoException("O rebelde " + rebelde.getNome() + " não possui o item " + tipoItem.getItem()
					+ " para ser removido.");
		}

		this.rebeldeRepository.save(rebelde);
	}

	/**
	 * Método responsável por validar uma negociação de itens entre dois rebeldes
	 */
	private void validarNegociacao(Rebelde rebelde1, Rebelde rebelde2, Long qtdPontosRebelde1, Long qtdPontosRebelde2)
			throws NegociacaoException {
		// Valida se o rebelde 1 é um traidor
		if (rebelde1.getIsTraidor()) {
			throw new NegociacaoException(
					"O rebelde " + rebelde1.getNome() + " é um traidor e não pode realizar negociações");
		}

		// Valida se o rebelde 2 é um traidor
		if (rebelde2.getIsTraidor()) {
			throw new NegociacaoException(
					"O rebelde " + rebelde2.getNome() + " é um traidor e não pode realizar negociações");
		}

		// Valida se o rebelde 1 adicionou itens à negociação
		if (qtdPontosRebelde1 <= 0) {
			throw new NegociacaoException("O rebelde " + rebelde1.getNome() + " não adicionou itens à negociação.");
		}

		// Valida se o rebelde 2 adicionou itens à negociação
		if (qtdPontosRebelde2 <= 0) {
			throw new NegociacaoException("O rebelde " + rebelde2.getNome() + " não adicionou itens à negociação.");
		}

		// Valida se o rebelde 1 possui pontos suficientes para realizar a negociação
		if (qtdPontosRebelde1 > this.calcularPontosRebelde(rebelde1)) {
			throw new NegociacaoException(
					"O rebelde " + rebelde1.getNome() + " não possui itens suficientes para realizar a negociação.");
		}

		// Valida se o rebelde 2 possui pontos suficientes para realizar a negociação
		if (qtdPontosRebelde2 > this.calcularPontosRebelde(rebelde2)) {
			throw new NegociacaoException(
					"O rebelde " + rebelde2.getNome() + " não possui itens suficientes para realizar a negociação.");
		}

		// Valida se os negociantes possuem pontuação equivalente
		if (qtdPontosRebelde1 != qtdPontosRebelde2) {
			throw new NegociacaoException("Os itens dos negociantes não possuem pontuação equivalente.");
		}
	}

	/**
	 * Método responsável por calcular pontos de um negociante
	 */
	private Long calcularQtdPontosItens(List<TipoItemInventario> itensNegociante) {
		Long qtdPontosItens = 0l;

		if (itensNegociante != null && itensNegociante.size() > 0) {
			for (TipoItemInventario tipoItemInventario : itensNegociante) {
				qtdPontosItens += tipoItemInventario.getValor();
			}
		}

		return qtdPontosItens;
	}

	/**
	 * Método responsável por listar todos os rebeldes
	 */
	@Override
	public List<Rebelde> listarTraidores() {
		return this.rebeldeRepository.findByIsTraidorIsTrue();
	}

	/**
	 * Método responsável por calcular pontos do inventário de um rebelde
	 */
	@Override
	public Long calcularPontosRebelde(Rebelde rebelde) {
		Long pontosRebelde = 0l;

		for (ItemInventario item : rebelde.getItensInventario()) {
			pontosRebelde += item.getTipo().getValor();
		}

		return pontosRebelde;
	}

}
