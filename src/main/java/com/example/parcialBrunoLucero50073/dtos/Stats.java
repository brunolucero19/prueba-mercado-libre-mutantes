package com.example.parcialBrunoLucero50073.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Stats {

    @JsonProperty("count_mutant")
    private long countMutant;

    @JsonProperty("count_human")
    private long countHuman;

    @JsonProperty("ratio")
    private double ratio;

}
