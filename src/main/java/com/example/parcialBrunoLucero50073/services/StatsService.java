package com.example.parcialBrunoLucero50073.services;

import com.example.parcialBrunoLucero50073.dtos.Stats;
import com.example.parcialBrunoLucero50073.repositories.IndividuoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class StatsService {
    @Autowired
    private IndividuoRepository individuoRepository;

    public Stats getStats() {
        long countMutant = individuoRepository.countMutant();
        long countHuman = individuoRepository.countNoMutant();
        double ratio = 0;
        if(countHuman != 0){
            ratio = (double) countMutant / countHuman;
        }
        return
                Stats.builder().countMutant(countMutant).countHuman(countHuman).ratio(ratio).build();
    }
}
