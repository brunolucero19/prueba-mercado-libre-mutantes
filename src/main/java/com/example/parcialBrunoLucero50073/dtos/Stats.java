package com.example.parcialBrunoLucero50073.dtos;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Stats {
    private long countMutant;
    private long countHuman;
    private double ratio;
}
