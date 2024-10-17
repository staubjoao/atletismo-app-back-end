package com.br.atletismo.serviceTest;

import com.br.atletismo.model.Atleta;
import com.br.atletismo.repository.AtletaRepository;
import com.br.atletismo.service.AtletaService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AtletaServiceTest {

    @Mock
    private AtletaRepository atletaRepository;

    @InjectMocks
    private AtletaService atletaService;

    public AtletaServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveAtleta() {
        Atleta atleta = new Atleta();
        atleta.setNome("João");

        when(atletaRepository.save(atleta)).thenReturn(atleta);

        Atleta result = atletaService.save(atleta);

        assertNotNull(result);
        assertEquals("João", result.getNome());
        verify(atletaRepository, times(1)).save(atleta);
    }
}
