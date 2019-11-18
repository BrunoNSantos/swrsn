package com.brunonsantos.swrsn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.brunonsantos.swrsn.enums.TipoItemInventario;

import lombok.Data;

/**
 * @author bruno
 *
 */
@Data
@Entity
@Table(schema = "public", name = "item_inventario")
public class ItemInventario {

	@Id
	@SequenceGenerator(name = "itemInventarioSeq", sequenceName = "public.item_inventario_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemInventarioSeq")
	@Column(name = "id")
	private Long id;

	@Column(name = "tipo")
	@Enumerated(EnumType.STRING)
	private TipoItemInventario tipo;
}
