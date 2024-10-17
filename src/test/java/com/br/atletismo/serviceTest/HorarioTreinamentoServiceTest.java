package com.br.atletismo.serviceTest;

import com.br.atletismo.model.HorarioTreinamento;
import com.br.atletismo.repository.HorarioTreinamentoRepository;
import com.br.atletismo.service.HorarioTreinamentoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class HorarioTreinamentoServiceTest {

    @Mock
    private HorarioTreinamentoRepository horarioTreinamentoRepository;

    @InjectMocks
    private HorarioTreinamentoService horarioTreinamentoService;

    public HorarioTreinamentoServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveHorarioTreinamento() {
        HorarioTreinamento horario = new HorarioTreinamento();
        horario.setDescricao("teste");

        when(horarioTreinamentoRepository.save(horario)).thenReturn(horario);

        HorarioTreinamento result = horarioTreinamentoService.save(horario);

        assertNotNull(result);
        assertEquals("teste", result.getDescricao());
        verify(horarioTreinamentoRepository, times(1)).save(horario);
    }
}

