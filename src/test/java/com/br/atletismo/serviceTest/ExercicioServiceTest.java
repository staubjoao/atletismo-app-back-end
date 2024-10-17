package com.br.atletismo.serviceTest;

import com.br.atletismo.model.Exercicio;
import com.br.atletismo.repository.ExercicioRepository;
import com.br.atletismo.service.ExercicioService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ExercicioServiceTest {

    @Mock
    private ExercicioRepository exercicioRepository;

    @InjectMocks
    private ExercicioService exercicioService;

    public ExercicioServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveExercicio() {
        Exercicio exercicio = new Exercicio();
        exercicio.setDescricao("Corrida");

        when(exercicioRepository.save(exercicio)).thenReturn(exercicio);

        Exercicio result = exercicioService.save(exercicio);

        assertNotNull(result);
        assertEquals("Corrida", result.getDescricao());
        verify(exercicioRepository, times(1)).save(exercicio);
    }
}
