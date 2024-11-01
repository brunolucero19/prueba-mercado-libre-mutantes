package com.example.parcialBrunoLucero50073.services;

import com.example.parcialBrunoLucero50073.entities.Individuo;
import com.example.parcialBrunoLucero50073.repositories.IndividuoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class IndividuoService {

    @Autowired
    private IndividuoRepository individuoRepository;

    public IndividuoService(IndividuoRepository individuoRepository) {
        this.individuoRepository = individuoRepository;
    }


    @Transactional
    public boolean isMutant(String[] dna) throws Exception {

        validateDNASequence(dna);

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
        Set<Character> secuenciasEncontradas = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char letraActual = dna[i].charAt(j);

                // Verificar en horizontal
                if (j + 3 < n &&
                        dna[i].charAt(j) == dna[i].charAt(j + 1) &&
                        dna[i].charAt(j + 1) == dna[i].charAt(j + 2) &&
                        dna[i].charAt(j + 2) == dna[i].charAt(j + 3)) {
                    secuenciasEncontradas.add(letraActual);
                }

                // Verificar en vertical
                if (i + 3 < n &&
                        dna[i].charAt(j) == dna[i + 1].charAt(j) &&
                        dna[i + 1].charAt(j) == dna[i + 2].charAt(j) &&
                        dna[i + 2].charAt(j) == dna[i + 3].charAt(j)) {
                    secuenciasEncontradas.add(letraActual);
                }

                // Verificar diagonal descendente
                if (i + 3 < n && j + 3 < n &&
                        dna[i].charAt(j) == dna[i + 1].charAt(j + 1) &&
                        dna[i + 1].charAt(j + 1) == dna[i + 2].charAt(j + 2) &&
                        dna[i + 2].charAt(j + 2) == dna[i + 3].charAt(j + 3)) {
                    secuenciasEncontradas.add(letraActual);
                }

                // Verificar diagonal ascendente
                if (i - 3 >= 0 && j + 3 < n &&
                        dna[i].charAt(j) == dna[i - 1].charAt(j + 1) &&
                        dna[i - 1].charAt(j + 1) == dna[i - 2].charAt(j + 2) &&
                        dna[i - 2].charAt(j + 2) == dna[i - 3].charAt(j + 3)) {
                    secuenciasEncontradas.add(letraActual);
                }

                // Verificar si ya se encontraron más de una secuencia
                if (secuenciasEncontradas.size() > 1) {
                    return true;
                }
            }
        }

        return false;
    }

    public void validateDNASequence (String[] dna) throws Exception{

        //Validar que no sea null
        if(dna == null){
            throw new Exception("La secuencia no puede ser null.");
        }

        int n = dna.length;

        //Validar que no sea un array vacío
        if(n == 0){
            throw new Exception("La secuencia de ADN no puede estar vacía.");
        }

        // Validar que sea una matriz de NxN y que no contenga null
        for(int i=0; i<n; i++){

            if(dna[i] == null){
                throw new Exception("La secuencia de ADN no puede contener null dentro del array.");
            }
            if(dna[i].length() != n){
                throw new Exception("La secuencia de ADN debe tener la misma cantidad de filas y columnas.");
            }

            //Validar que la cadena contenga solo letras A, T, C y G
            for(int j=0; j<n; j++){
                char c = dna[i].charAt(j);
                if(c != 'A' && c != 'T' && c != 'C' && c != 'G'){
                    throw new Exception("La secuencia de ADN tiene caracteres inválidos. Sólo debe tener A, T, C ó G.");
                }
            }
        }
    }
}
