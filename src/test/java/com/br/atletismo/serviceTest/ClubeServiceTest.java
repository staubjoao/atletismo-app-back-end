package com.br.atletismo.serviceTest;

import com.br.atletismo.model.Clube;
import com.br.atletismo.repository.ClubeRepository;
import com.br.atletismo.service.ClubeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ClubeServiceTest {

    @Mock
    private ClubeRepository clubeRepository;

    @InjectMocks
    private ClubeService clubeService;

    public ClubeServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveClube() {
        Clube clube = new Clube();
        clube.setNome("Clube A");

        when(clubeRepository.save(clube)).thenReturn(clube);

        Clube result = clubeService.save(clube);

        assertNotNull(result);
        assertEquals("Clube A", result.getNome());
        verify(clubeRepository, times(1)).save(clube);
    }
}
