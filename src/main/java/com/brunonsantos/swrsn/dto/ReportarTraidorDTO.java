package com.brunonsantos.swrsn.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author bruno
 *
 */
@ApiModel(value = "Reportar Traidor", description = "Dados necessários para reportar um traidor.")
@Getter
@Setter
public class ReportarTraidorDTO {

	@ApiModelProperty(value = "Id do rebelde que está reportando o traidor", required = true)
	private Long idRebelde;

	@ApiModelProperty(value = "Id do rebelde traidor", required = true)
	private Long idTraidor;

}
