package com.br.atletismo.serviceTest;

import com.br.atletismo.model.Evento;
import com.br.atletismo.repository.EventoRepository;
import com.br.atletismo.service.EventoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class EventoServiceTest {

    @Mock
    private EventoRepository eventoRepository;

    @InjectMocks
    private EventoService eventoService;

    public EventoServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveEvento() {
        Evento evento = new Evento();
        evento.setNome("Campeonato Regional");

        when(eventoRepository.save(evento)).thenReturn(evento);

        Evento result = eventoService.save(evento);

        assertNotNull(result);
        assertEquals("Campeonato Regional", result.getNome());
        verify(eventoRepository, times(1)).save(evento);
    }
}
