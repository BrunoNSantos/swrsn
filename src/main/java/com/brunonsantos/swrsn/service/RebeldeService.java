package com.brunonsantos.swrsn.service;

import java.util.List;

import com.brunonsantos.swrsn.dto.AtualizarLocalizacaoDTO;
import com.brunonsantos.swrsn.dto.NegociarItensDTO;
import com.brunonsantos.swrsn.dto.RebeldeDTO;
import com.brunonsantos.swrsn.dto.ReportarTraidorDTO;
import com.brunonsantos.swrsn.exception.NegociacaoException;
import com.brunonsantos.swrsn.exception.RegistroNaoEncontradoException;
import com.brunonsantos.swrsn.model.Rebelde;

/**
 * @author bruno
 *
 */
public interface RebeldeService {

	Rebelde adicionarRebelde(RebeldeDTO rebeldeDTO);

	List<Rebelde> listarTodosRebeldes();

	Rebelde encontrarRebelde(Long id) throws RegistroNaoEncontradoException;

	void atualizarLocalizacao(AtualizarLocalizacaoDTO params) throws RegistroNaoEncontradoException;

	void reportarTraidor(ReportarTraidorDTO params) throws RegistroNaoEncontradoException;

	void negociarItens(NegociarItensDTO params) throws RegistroNaoEncontradoException, NegociacaoException;

	List<Rebelde> listarTraidores();

	Long calcularPontosRebelde(Rebelde traidor);
}
