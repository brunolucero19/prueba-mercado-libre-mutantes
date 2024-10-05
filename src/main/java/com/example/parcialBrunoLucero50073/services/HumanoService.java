package com.example.parcialBrunoLucero50073.services;

import com.example.parcialBrunoLucero50073.entities.Humano;
import com.example.parcialBrunoLucero50073.repositories.HumanoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HumanoService  {

    @Autowired
    private HumanoRepository humanoRepository;

    public HumanoService(HumanoRepository humanoRepository){
        this.humanoRepository = humanoRepository;
    }


    @Transactional
    public boolean isMutant(String[] dna) throws Exception{
        //Verificar si ya existe un humano con ese DNA
        if(humanoRepository.existsByDna(dna)){
            throw new Exception("El ADN ya existe en la base de datos");
        }

        //Crear al humano y persistirlo en la BD
        Humano humano = Humano.builder().dna(dna).build();
        humanoRepository.save(humano);
        int cantSecuenciasIguales = contarSecuenciasIguales(dna);

        //Si cantSecuenciasIguales > 1 entonces es mutante
        return cantSecuenciasIguales > 1;
    }

    private int contarSecuenciasIguales(String[] dna){
        int n = dna.length;
        int cantSecuenciasIguales = 0;

        for (int i=0; i<n; i++){
            for (int j=0; j<n; j++){
                //Verificar en horizontal
                if(j+3 < n && (dna[i].charAt(j) == dna[i].charAt(j+1)) && (dna[i].charAt(j+1) == dna[i].charAt(j+2)) && (dna[i].charAt(j+2) == dna[i].charAt(j+3))){
                    cantSecuenciasIguales++;
                }
                //Verificar en vertical
                if(i+3 < n && (dna[i].charAt(j) == dna[i+1].charAt(j)) && (dna[i+1].charAt(j) == dna[i+2].charAt(j)) && (dna[i+2].charAt(j) == dna[i+3].charAt(j))){
                    cantSecuenciasIguales++;
                }
                //Verificar diagonal descendente
                if(i+3<n && j+3<n && (dna[i].charAt(j) == dna[i+1].charAt(j+1)) && (dna[i+1].charAt(j+1) == dna[i+2].charAt(j+2)) && (dna[i+2].charAt(j+2) == dna[i+3].charAt(j+3))){
                    cantSecuenciasIguales++;
                }
                //Verificar diagonal ascendente
                if(i-3 >=0 && j+3<n && (dna[i-3].charAt(j+3) == dna[i-2].charAt(j+2)) && (dna[i-2].charAt(j+2) == dna[i-1].charAt(j+1)) && (dna[i-1].charAt(j+1) == dna[i].charAt(j))){
                    cantSecuenciasIguales++;
                }
            }
        }
        return cantSecuenciasIguales;
    }
}
