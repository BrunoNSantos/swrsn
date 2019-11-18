package com.brunonsantos.swrsn.dto;

import com.brunonsantos.swrsn.enums.TipoItemInventario;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author bruno
 *
 */
@ApiModel(value = "Item de Relatório", description = "Item de Relatório de Relatório de Média de Tipo de Recurso por Rebelde")
@Getter
@Setter
public class RelatorioMediaTipoRecursoDTO {

	@ApiModelProperty(value = "Tipo de item de inventário", required = false)
	private TipoItemInventario tipo;

	@ApiModelProperty(value = "Média de item por rebelde", required = false)
	private Double media;

}
