package com.brunonsantos.swrsn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author bruno
 *
 */
@Data
@Entity
@Table(schema = "public", name = "report_traidor")
public class ReportTraidor {

	@Id
	@SequenceGenerator(name = "reportTraidorSeq", sequenceName = "public.report_traidor_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reportTraidorSeq")
	@Column(name = "id")
	private Long id;

	@JoinColumn(name = "id_rebelde")
	@OneToOne
	@NotNull
	private Rebelde rebelde;

	@JoinColumn(name = "id_traidor")
	@OneToOne
	@NotNull
	private Rebelde traidor;

}
