package com.daniele.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daniele.batch.model.Duplicata;

@Repository
public interface DuplicataRepository extends JpaRepository<Duplicata, String> {

}
