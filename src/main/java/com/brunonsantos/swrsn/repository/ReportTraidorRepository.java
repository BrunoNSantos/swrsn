package com.brunonsantos.swrsn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brunonsantos.swrsn.model.ReportTraidor;

/**
 * @author bruno
 *
 */
public interface ReportTraidorRepository extends JpaRepository<ReportTraidor, Long> {

	Long countByTraidorId(Long idTraidor);

}
