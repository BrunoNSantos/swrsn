package com.brunonsantos.swrsn.controllers;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brunonsantos.swrsn.dto.RelatorioMediaTipoRecursoDTO;
import com.brunonsantos.swrsn.service.RelatorioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author bruno
 *
 */
@Api
@RestController
@RequestMapping("/relatorio")
public class RelatorioController {

	@Resource
	private RelatorioService relatorioService;

	@ApiOperation(nickname = "Porcentagem de traidores", value = "Retorna a porcentagem de traidores", produces = "application/json")
	@GetMapping(value = "/porcentagemTraidores", produces = "application/json")
	public ResponseEntity<Long> porcentagemTraidores() {
		return ResponseEntity.ok().body(this.relatorioService.porcentagemTraidores());
	}

	@ApiOperation(nickname = "Porcentagem de rebeldes", value = "Retorna a porcentagem de rebeldes", produces = "application/json")
	@GetMapping(value = "/porcentagemRebeldes", produces = "application/json")
	public ResponseEntity<Long> porcentagemRebeldes() {
		return ResponseEntity.ok().body(this.relatorioService.porcentagemRebeldes());
	}

	@ApiOperation(nickname = "Média de recursos", value = "Retorna a quantidade média de cada tipo de recurso por rebelde", produces = "application/json")
	@GetMapping(value = "/mediaRecursos", produces = "application/json")
	public ResponseEntity<List<RelatorioMediaTipoRecursoDTO>> mediaRecursos() {
		return ResponseEntity.ok().body(this.relatorioService.mediaTipoRecursoPorRebelde());
	}

	@ApiOperation(nickname = "Pontos perditos para traidores", value = "Retorna a quantidade de pontos perdidos devido a traidores", produces = "application/json")
	@GetMapping(value = "/pontosPerdidosTraidores", produces = "application/json")
	public ResponseEntity<Long> pontosPerdidosTraidores() {
		return ResponseEntity.ok().body(this.relatorioService.pontosPerdidosDevidoATraidores());
	}
}
