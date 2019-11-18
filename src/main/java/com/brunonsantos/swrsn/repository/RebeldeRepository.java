package com.brunonsantos.swrsn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brunonsantos.swrsn.model.Rebelde;

/**
 * @author bruno
 *
 */
public interface RebeldeRepository extends JpaRepository<Rebelde, Long> {

	List<Rebelde> findByIsTraidorIsTrue();

}
