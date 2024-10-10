package com.example.parcialBrunoLucero50073.services;

import com.example.parcialBrunoLucero50073.entities.Individuo;
import com.example.parcialBrunoLucero50073.repositories.IndividuoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IndividuoService {

    @Autowired
    private IndividuoRepository individuoRepository;


    @Transactional
    public boolean isMutant(String[] dna) throws Exception{
        String dnaSequence = String.join(",", dna);

        //Verificar si ya existe un individuo con ese DNA
        Optional<Individuo> individuoExistente = individuoRepository.findByDna(dnaSequence);
        if(individuoExistente.isPresent()){
            return individuoExistente.get().isMutant();
        }

        boolean isMutant = checkDna(dna);
        //Si no existe, crear al individuo y persistirlo en la BD
        Individuo individuo = Individuo.builder().dna(dnaSequence).isMutant(isMutant).build();
        individuoRepository.save(individuo);

        return isMutant;
    }

    public boolean checkDna(String[] dna) {
        int n = dna.length;
        int cantSecuencias = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Verificar en horizontal
                if (j + 3 < n &&
                        dna[i].charAt(j) == dna[i].charAt(j + 1) &&
                        dna[i].charAt(j + 1) == dna[i].charAt(j + 2) &&
                        dna[i].charAt(j + 2) == dna[i].charAt(j + 3)) {
                    cantSecuencias++;
                }

                // Verificar en vertical
                if (i + 3 < n &&
                        dna[i].charAt(j) == dna[i + 1].charAt(j) &&
                        dna[i + 1].charAt(j) == dna[i + 2].charAt(j) &&
                        dna[i + 2].charAt(j) == dna[i + 3].charAt(j)) {
                    cantSecuencias++;
                }

                // Verificar diagonal descendente
                if (i + 3 < n && j + 3 < n &&
                        dna[i].charAt(j) == dna[i + 1].charAt(j + 1) &&
                        dna[i + 1].charAt(j + 1) == dna[i + 2].charAt(j + 2) &&
                        dna[i + 2].charAt(j + 2) == dna[i + 3].charAt(j + 3)) {
                    cantSecuencias++;
                }

                // Verificar diagonal ascendente
                if (i - 3 >= 0 && j + 3 < n &&
                        dna[i].charAt(j) == dna[i - 1].charAt(j + 1) &&
                        dna[i - 1].charAt(j + 1) == dna[i - 2].charAt(j + 2) &&
                        dna[i - 2].charAt(j + 2) == dna[i - 3].charAt(j + 3)) {
                    cantSecuencias++;
                }

                // Verificar si ya se encontraron más de una secuencia
                if (cantSecuencias > 1) {
                    return true;
                }
            }
        }

        return false;
    }



}
