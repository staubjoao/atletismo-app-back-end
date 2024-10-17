package com.br.atletismo.serviceTest;

import com.br.atletismo.model.SessaoTreinamento;
import com.br.atletismo.repository.SessaoTreinamentoRepository;
import com.br.atletismo.service.SessaoTreinamentoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class SessaoTreinamentoServiceTest {

    @Mock
    private SessaoTreinamentoRepository sessaoTreinamentoRepository;

    @InjectMocks
    private SessaoTreinamentoService sessaoTreinamentoService;

    public SessaoTreinamentoServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveSessaoTreinamento() {
        SessaoTreinamento sessao = new SessaoTreinamento();
        sessao.setFeedback("Treino 1");

        when(sessaoTreinamentoRepository.save(sessao)).thenReturn(sessao);

        SessaoTreinamento result = sessaoTreinamentoService.save(sessao);

        assertNotNull(result);
        assertEquals("Treino 1", result.getFeedback());
        verify(sessaoTreinamentoRepository, times(1)).save(sessao);
    }
}
