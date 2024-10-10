package com.example.parcialBrunoLucero50073.repositories;

import com.example.parcialBrunoLucero50073.entities.Individuo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IndividuoRepository extends BaseRepository<Individuo, Long> {

    Optional<Individuo> findByDna(String dna);

    //Contar la cantidad de mutantes en la BD
    @Query(value = "SELECT COUNT(ind) FROM Individuo ind WHERE ind.isMutant = true")
    long countMutant();

    //Contar la cantidad de no mutantes en la BD
    @Query(value = "SELECT COUNT(ind) FROM Individuo ind WHERE ind.isMutant = false")
    long countNoMutant();

}
