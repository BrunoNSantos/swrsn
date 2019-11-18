package com.brunonsantos.swrsn.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.brunonsantos.swrsn.dto.RelatorioMediaTipoRecursoDTO;
import com.brunonsantos.swrsn.enums.TipoItemInventario;
import com.brunonsantos.swrsn.model.ItemInventario;
import com.brunonsantos.swrsn.model.Rebelde;
import com.brunonsantos.swrsn.service.RebeldeService;
import com.brunonsantos.swrsn.service.RelatorioService;

/**
 * @author bruno
 *
 */
@Service
public class RelatorioServiceImpl implements RelatorioService {

	@Resource
	private RebeldeService rebeldeService;

	/**
	 * Método responsável por calcular a porcentagem de traidores entre os rebeldes
	 */
	@Override
	public Long porcentagemTraidores() {
		Long qtdTraidores = 0l;
		List<Rebelde> rebeldes = this.rebeldeService.listarTodosRebeldes();

		for (Rebelde rebelde : rebeldes) {
			if (rebelde.getIsTraidor()) {
				qtdTraidores++;
			}
		}

		return (qtdTraidores * 100) / rebeldes.size();
	}

	/**
	 * Método responsável por calcular a porcentagem de NÃO traidores entre os rebeldes
	 */
	@Override
	public Long porcentagemRebeldes() {
		Long qtdRebeldes = 0l;
		List<Rebelde> rebeldes = this.rebeldeService.listarTodosRebeldes();

		for (Rebelde rebelde : rebeldes) {
			if (!rebelde.getIsTraidor()) {
				qtdRebeldes++;
			}
		}

		return (qtdRebeldes * 100) / rebeldes.size();
	}

	/**
	 * Método responsável por calcular a média de cada tipo de item por rebelde
	 */
	@Override
	public List<RelatorioMediaTipoRecursoDTO> mediaTipoRecursoPorRebelde() {
		List<RelatorioMediaTipoRecursoDTO> report = new ArrayList<RelatorioMediaTipoRecursoDTO>();
		List<Rebelde> rebeldes = this.rebeldeService.listarTodosRebeldes();

		// Média de Arma
		RelatorioMediaTipoRecursoDTO armas = new RelatorioMediaTipoRecursoDTO();
		armas.setTipo(TipoItemInventario.ARMA);
		armas.setMedia(this.getMediaPorTipoRecurso(TipoItemInventario.ARMA, rebeldes));
		report.add(armas);

		// Média de Munições
		RelatorioMediaTipoRecursoDTO municoes = new RelatorioMediaTipoRecursoDTO();
		municoes.setTipo(TipoItemInventario.MUNICAO);
		municoes.setMedia(this.getMediaPorTipoRecurso(TipoItemInventario.MUNICAO, rebeldes));
		report.add(municoes);

		// Média de Águas
		RelatorioMediaTipoRecursoDTO aguas = new RelatorioMediaTipoRecursoDTO();
		aguas.setTipo(TipoItemInventario.AGUA);
		aguas.setMedia(this.getMediaPorTipoRecurso(TipoItemInventario.AGUA, rebeldes));
		report.add(aguas);

		// Média de Comidas
		RelatorioMediaTipoRecursoDTO comidas = new RelatorioMediaTipoRecursoDTO();
		comidas.setTipo(TipoItemInventario.COMIDA);
		comidas.setMedia(this.getMediaPorTipoRecurso(TipoItemInventario.COMIDA, rebeldes));
		report.add(comidas);

		return report;
	}

	/**
	 * Método responsável por calcular a média de um tipo de item por rebelde
	 */
	private Double getMediaPorTipoRecurso(TipoItemInventario tipoItem, List<Rebelde> rebeldes) {
		Double qtd = 0d;

		for (Rebelde rebelde : rebeldes) {
			for (ItemInventario itemInventario : rebelde.getItensInventario()) {
				if (itemInventario.getTipo() == tipoItem) {
					qtd++;
				}
			}
		}

		return Double.valueOf(qtd / Double.valueOf(rebeldes.size()));
	}

	/**
	 * Método responsável por calcular os pontos de inventário perdidos para os traidores
	 */
	@Override
	public Long pontosPerdidosDevidoATraidores() {
		Long qtdPontosTraidores = 0l;
		List<Rebelde> traidores = this.rebeldeService.listarTraidores();

		for (Rebelde traidor : traidores) {
			qtdPontosTraidores = this.rebeldeService.calcularPontosRebelde(traidor);
		}

		return qtdPontosTraidores;
	}

}
