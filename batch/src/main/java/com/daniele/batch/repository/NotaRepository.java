package com.daniele.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daniele.batch.model.Nota;

@Repository
public interface NotaRepository extends JpaRepository<Nota, String>{

}
