package com.example.parcialBrunoLucero50073.services;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class IndividuoServiceTest {

    @Autowired
    private IndividuoService individuoService;


    //Test para probar secuencias horizontales
    @Test
    public void testHorizontal() {
        String[] dna = {
                "AAAAGA",
                "CAGTGC",
                "TTATGT",
                "AGATGG",
                "CCCCTA",
                "TCACTG"
        };
        assertTrue(individuoService.isMutant(dna));
    }

    //Test para probar secuencias verticales
    @Test
    public void testVertical() {
        String[] dna = {
                "ATGCGA",
                "AAGCGC",
                "ATACGT",
                "AGACGG",
                "CCTGTA",
                "TCACTG"
        };
        assertTrue(individuoService.isMutant(dna));
    }

    //Test para probar diagonales descendentes
    @Test
    public void testDescendingDiagonal() {
        String[] dna = {
                "ATGCGA",
                "CATTGC",
                "TTATGT",
                "AGAATG",
                "CCGCTA",
                "TCACTG"
        };
        assertTrue(individuoService.isMutant(dna));
    }

    //Test para probar diagonales ascendentes
    @Test
    public void testAscendingDiagonal() {
        String[] dna = {
                "ATGCGA",
                "CATTAC",
                "TTAACT",
                "AGACTG",
                "CTCCTA",
                "TCACTG"
        };
        assertTrue(individuoService.isMutant(dna));
    }

    //Test para probar que no es mutante
    @Test
    public void testNoMutant()  {
        String[] dna = {
                "ATGCGA",
                "CGTTAC",
                "TTAGCT",
                "AGAATG",
                "CTCCTA",
                "TCACTG"
        };
        assertFalse(individuoService.isMutant(dna));
    }

    //Pruebas unitarias brindadas por el profesor
    @Test
    public void test1(){
        String[] dna = {
                "AAAA",
                "CCCC",
                "TCAG",
                "GGTC"
        };
        assertTrue(individuoService.isMutant(dna));
    }

    @Test
    public void test2(){
        String[] dna = {
                "TGAC",
                "AGCC",
                "TGAC",
                "GGTC"
        };
        assertTrue(individuoService.isMutant(dna));
    }

    @Test
    public void test3(){
        String[] dna = {
                "AAAT",
                "AACC",
                "AAAC",
                "CGGG"
        };
        assertFalse(individuoService.isMutant(dna));
    }

    @Test
    public void test4(){
        String[] dna = {
                "TGAC",
                "ATCC",
                "TAAC",
                "GGTC"
        };
        assertFalse(individuoService.isMutant(dna));
    }

    @Test
    public void test5(){
        String[] dna = {
                "AAAA",
                "AAAA",
                "AAAA",
                "AAAA"
        };
        assertFalse(individuoService.isMutant(dna));
    }

    @Test
    public void test6(){
        String[] dna = {
                "TCGGGTGAT",
                "TGATCCTTT",
                "TACGAGTGA",
                "AAATGTACG",
                "ACGAGTGCT",
                "AGACACATG",
                "GAATTCCAA",
                "ACTACGACC",
                "TGAGTATCC"
        };
        assertTrue(individuoService.isMutant(dna));
    }

    @Test
    public void test7(){
        String[] dna = {
                "TTTTTTTTT",
                "TTTTTTTTT",
                "TTTTTTTTT",
                "TTTTTTTTT",
                "CCGACCAGT",
                "GGCACTCCA",
                "AGGACACTA",
                "CAAAGGCAT",
                "GCAGTCCCC"

        };
        assertTrue(individuoService.isMutant(dna));
    }
}

