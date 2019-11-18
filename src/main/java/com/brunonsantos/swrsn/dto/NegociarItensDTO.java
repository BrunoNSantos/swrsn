package com.brunonsantos.swrsn.dto;

import java.util.List;

import com.brunonsantos.swrsn.enums.TipoItemInventario;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author bruno
 *
 */
@ApiModel(value = "Negociar itens", description = "Dados para realizar a negociação de itens entre dois rebeldes")
@Getter
@Setter
public class NegociarItensDTO {

	@ApiModelProperty(value = "Id do primeiro rebelde", required = true)
	private Long idRebeldeNegociante1;

	@ApiModelProperty(value = "Id do segundo rebelde", required = true)
	private Long idRebeldeNegociante2;

	@ApiModelProperty(value = "Lista de itens do primeiro rebelde que será negociada", required = true)
	private List<TipoItemInventario> itensNegociante1;

	@ApiModelProperty(value = "Lista de itens do segundo rebelde que será negociada", required = true)
	private List<TipoItemInventario> itensNegociante2;

}
