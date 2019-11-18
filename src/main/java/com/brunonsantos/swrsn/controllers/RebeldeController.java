package com.brunonsantos.swrsn.controllers;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brunonsantos.swrsn.dto.AtualizarLocalizacaoDTO;
import com.brunonsantos.swrsn.dto.NegociarItensDTO;
import com.brunonsantos.swrsn.dto.RebeldeDTO;
import com.brunonsantos.swrsn.dto.ReportarTraidorDTO;
import com.brunonsantos.swrsn.exception.NegociacaoException;
import com.brunonsantos.swrsn.exception.RegistroNaoEncontradoException;
import com.brunonsantos.swrsn.service.RebeldeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author bruno
 *
 */
@Api
@RestController
@RequestMapping("/rebelde")
public class RebeldeController {

	@Resource
	private RebeldeService rebeldeService;

	@ApiOperation(nickname = "Adicionar rebelde", value = "Cria um novo rebelde", produces = "application/json")
	@PostMapping(value = "/adicionar", produces = "application/json")
	public ResponseEntity<RebeldeDTO> adicionarRebelde(
			@ApiParam(value = "Dados para adicionar um novo rebelde") @RequestBody RebeldeDTO rebeldeDTORequest) {
		return ResponseEntity.ok().body(new RebeldeDTO(this.rebeldeService.adicionarRebelde(rebeldeDTORequest)));
	}

	@ApiOperation(nickname = "Atualizar localização", value = "Atualiza a localização de um rebelde", produces = "application/json")
	@PutMapping(value = "/atualizarLocalizacao", produces = "application/json")
	public ResponseEntity<Void> atualizarLocalizacao(
			@ApiParam(value = "Dados atualizar a localização de um rebelde") @RequestBody AtualizarLocalizacaoDTO params)
			throws RegistroNaoEncontradoException {
		this.rebeldeService.atualizarLocalizacao(params);
		return ResponseEntity.ok().build();
	}

	@ApiOperation(nickname = "Reportar traidor", value = "Reporta um rebelde traidor", produces = "application/json")
	@PutMapping(value = "/reportarTraidor", produces = "application/json")
	public ResponseEntity<Void> reportarTraidor(
			@ApiParam(value = "Dados para reportar um traidor") @RequestBody ReportarTraidorDTO params)
			throws RegistroNaoEncontradoException {
		this.rebeldeService.reportarTraidor(params);
		return ResponseEntity.ok().build();
	}

	@ApiOperation(nickname = "Listar todos", value = "Retorna a lista de todos os rebeldes", produces = "application/json")
	@GetMapping(value = "/listarTodos", produces = "application/json")
	public ResponseEntity<List<RebeldeDTO>> listarTodos() {
		return ResponseEntity.ok().body(RebeldeDTO.convertList(this.rebeldeService.listarTodosRebeldes()));
	}

	@ApiOperation(nickname = "Negociar itens", value = "Realiza a negociação de itens entre dois rebeldes", produces = "application/json")
	@PostMapping(value = "/negociarItens", produces = "application/json")
	public ResponseEntity<Void> negociarItens(
			@ApiParam(value = "Dados para rrealizar uma negociação") @RequestBody NegociarItensDTO params)
			throws RegistroNaoEncontradoException, NegociacaoException {
		this.rebeldeService.negociarItens(params);
		return ResponseEntity.ok().build();
	}

}
