package com.brunonsantos.swrsn.service;

import java.util.List;

import com.brunonsantos.swrsn.dto.RelatorioMediaTipoRecursoDTO;

/**
 * @author bruno
 *
 */
public interface RelatorioService {

	Long porcentagemTraidores();

	Long porcentagemRebeldes();

	List<RelatorioMediaTipoRecursoDTO> mediaTipoRecursoPorRebelde();

	Long pontosPerdidosDevidoATraidores();
}
