package com.brunonsantos.swrsn.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * @author bruno
 *
 */
@Data
@Entity
@Table(schema = "public", name = "rebelde")
public class Rebelde {

	@Id
	@SequenceGenerator(name = "rebeldeSeq", sequenceName = "public.rebelde_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rebeldeSeq")
	@Column(name = "id")
	private Long id;

	@NotBlank
	@Size(max = 50)
	@Column(name = "nome", length = 45, nullable = false)
	private String nome;

	@NotNull
	@Column(name = "idade")
	private Integer idade;

	@NotBlank
	@Column(name = "genero")
	private String genero;

	@NotNull
	@Column(name = "latitude")
	private BigDecimal latitude;

	@NotNull
	@Column(name = "longitude")
	private BigDecimal longitude;

	@NotBlank
	@Column(name = "nome_localizacao")
	private String nomeLocalizacao;

	@NotNull
	@Column(name = "traidor")
	private Boolean isTraidor;

	@JoinColumn(name = "id_rebelde", nullable = false)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<ItemInventario> itensInventario;

	public Rebelde() {
		this.isTraidor = false;
	}

	public List<ItemInventario> getItensInventario() {
		if (this.itensInventario == null) {
			this.itensInventario = new ArrayList<>();
		}
		return Collections.unmodifiableList(this.itensInventario);
	}

	public boolean addItemInventario(ItemInventario itemInventario) {
		if (this.itensInventario == null) {
			this.itensInventario = new ArrayList<>();
		}
		return this.itensInventario.add(itemInventario);
	}

	public boolean removerItemInventario(ItemInventario itemInventario) {
		if (this.itensInventario != null) {
			return this.itensInventario.remove(itemInventario);
		}
		return false;
	}

	public boolean atualizarItensInventario(Collection<ItemInventario> itensInventario) {
		if (this.itensInventario == null) {
			this.itensInventario = new ArrayList<ItemInventario>();
		}
		boolean result = this.itensInventario.addAll(itensInventario);
		result = this.itensInventario.retainAll(itensInventario) || result;
		return result;
	}
}
