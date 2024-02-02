package com.irene.poxylari.emicalculator.repository;

import com.irene.poxylari.emicalculator.model.Input;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InputRepository extends JpaRepository<Input, Long> {
}
