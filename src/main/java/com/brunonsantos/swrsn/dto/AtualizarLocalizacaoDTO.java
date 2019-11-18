package com.brunonsantos.swrsn.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author bruno
 *
 */
@ApiModel(value = "Atualizar localização", description = "Dados para realizar a atualização da localização de um rebelde")
@Getter
@Setter
public class AtualizarLocalizacaoDTO {

	@ApiModelProperty(value = "Id do rebelde que será atualizado", required = true)
	private Long idRebelde;

	@ApiModelProperty(value = "Latitude da localização do rebelde que será atualizado", required = true)
	private BigDecimal latitude;

	@ApiModelProperty(value = "Longitude da localização do rebelde que será atualizado", required = true)
	private BigDecimal longitude;

	@ApiModelProperty(value = "Nome da localização do rebelde que será atualizado", required = true)
	private String nomeLocalizacao;

}
