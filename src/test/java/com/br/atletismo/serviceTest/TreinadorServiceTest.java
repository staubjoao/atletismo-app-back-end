package com.br.atletismo.serviceTest;

import com.br.atletismo.model.Treinador;
import com.br.atletismo.repository.TreinadorRepository;
import com.br.atletismo.service.TreinadorService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TreinadorServiceTest {

    @Mock
    private TreinadorRepository treinadorRepository;

    @InjectMocks
    private TreinadorService treinadorService;

    public TreinadorServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveTreinador() {
        Treinador treinador = new Treinador();
        treinador.setNome("Carlos");

        when(treinadorRepository.save(treinador)).thenReturn(treinador);

        Treinador result = treinadorService.save(treinador);

        assertNotNull(result);
        assertEquals("Carlos", result.getNome());
        verify(treinadorRepository, times(1)).save(treinador);
    }
}
