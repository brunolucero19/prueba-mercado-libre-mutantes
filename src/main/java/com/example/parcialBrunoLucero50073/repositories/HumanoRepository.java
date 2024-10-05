package com.example.parcialBrunoLucero50073.repositories;

import com.example.parcialBrunoLucero50073.entities.Humano;
import org.springframework.stereotype.Repository;

@Repository
public interface HumanoRepository extends BaseRepository<Humano, Long> {

    boolean existsByDna(String[] dna);
}
