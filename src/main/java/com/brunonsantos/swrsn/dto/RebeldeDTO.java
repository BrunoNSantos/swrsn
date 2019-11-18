package com.brunonsantos.swrsn.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.brunonsantos.swrsn.enums.TipoItemInventario;
import com.brunonsantos.swrsn.model.ItemInventario;
import com.brunonsantos.swrsn.model.Rebelde;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author bruno
 *
 */
@ApiModel(value = "Rebelde", description = "Dados de um rebelde.")
@Getter
@Setter
@NoArgsConstructor
public class RebeldeDTO {

	@ApiModelProperty(value = "Identificador do rebelde", required = false)
	private Long id;

	@ApiModelProperty(value = "Nome do rebelde", required = true)
	private String nome;

	@ApiModelProperty(value = "Idade do rebelde", required = true)
	private Integer idade;

	@ApiModelProperty(value = "Genero do rebelde", required = true)
	private String genero;

	@ApiModelProperty(value = "Latitude da última localização do rebelde", required = true)
	private BigDecimal latitude;

	@ApiModelProperty(value = "Longitude da última localização do rebelde", required = true)
	private BigDecimal longitude;

	@ApiModelProperty(value = "Nome da última localização do rebelde", required = true)
	private String nomeLocalizacao;

	@ApiModelProperty(value = "Itens do inventário do rebelde", required = true)
	private List<TipoItemInventario> itensInventario;

	@ApiModelProperty(value = "Flag que indica se o rebelde é traidor", required = false)
	private Boolean isTraidor;

	public RebeldeDTO(Rebelde rebelde) {
		this.id = rebelde.getId();
		this.nome = rebelde.getNome();
		this.idade = rebelde.getIdade();
		this.genero = rebelde.getGenero();
		this.latitude = rebelde.getLatitude();
		this.longitude = rebelde.getLongitude();
		this.nomeLocalizacao = rebelde.getNomeLocalizacao();
		this.isTraidor = rebelde.getIsTraidor();
		this.itensInventario = this.preencherInventario(rebelde.getItensInventario());
	}

	private List<TipoItemInventario> preencherInventario(List<ItemInventario> itensInventario) {
		List<TipoItemInventario> itensInventarioDTO = new ArrayList<TipoItemInventario>();

		for (ItemInventario itemInventario : itensInventario) {
			itensInventarioDTO.add(itemInventario.getTipo());
		}

		return itensInventarioDTO;
	}

	public static List<RebeldeDTO> convertList(List<Rebelde> listarTodosRebeldes) {
		List<RebeldeDTO> listDTO = new ArrayList<RebeldeDTO>();

		for (Rebelde rebelde : listarTodosRebeldes) {
			listDTO.add(new RebeldeDTO(rebelde));
		}

		return listDTO;
	}
}
