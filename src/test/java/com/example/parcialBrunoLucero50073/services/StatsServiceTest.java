package com.example.parcialBrunoLucero50073.services;

import com.example.parcialBrunoLucero50073.dtos.Stats;
import com.example.parcialBrunoLucero50073.repositories.IndividuoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class StatsServiceTest {

    @Mock
    private IndividuoRepository individuoRepository;

    @InjectMocks
    private StatsService statsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetStatsWithMutantsAndHumans() {
        // Escenario: hay mutantes y humanos en la base de datos
        when(individuoRepository.countMutant()).thenReturn(40L);
        when(individuoRepository.countNoMutant()).thenReturn(100L);

        Stats stats = statsService.getStats();

        assertEquals(40L, stats.getCountMutant());
        assertEquals(100L, stats.getCountHuman());
        assertEquals(0.4, stats.getRatio());  // 40 / 100 = 0.4
    }

    @Test
    void testGetStatsWithOnlyMutants() {
        // Escenario: solo hay mutantes, ningún humano
        when(individuoRepository.countMutant()).thenReturn(50L);
        when(individuoRepository.countNoMutant()).thenReturn(0L);

        Stats stats = statsService.getStats();

        assertEquals(50L, stats.getCountMutant());
        assertEquals(0L, stats.getCountHuman());
        assertEquals(0.0, stats.getRatio());  // ratio debería ser 0.0 porque no hay humanos
    }

    @Test
    void testGetStatsWithNoMutants() {
        // Escenario: hay humanos pero ningún mutante
        when(individuoRepository.countMutant()).thenReturn(0L);
        when(individuoRepository.countNoMutant()).thenReturn(100L);

        Stats stats = statsService.getStats();

        assertEquals(0L, stats.getCountMutant());
        assertEquals(100L, stats.getCountHuman());
        assertEquals(0.0, stats.getRatio());  // 0 / 100 = 0.0
    }
}
