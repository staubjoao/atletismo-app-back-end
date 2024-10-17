package com.br.atletismo.service;

import com.br.atletismo.model.ItemSessaoTreinamento;
import com.br.atletismo.repository.ItemSessaoTreinamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemSessaoTreinamentoService {

    @Autowired
    private ItemSessaoTreinamentoRepository itemSessaoTreinamentoRepository;

    public List<ItemSessaoTreinamento> findAll() {
        return itemSessaoTreinamentoRepository.findAll();
    }

    public Optional<ItemSessaoTreinamento> findById(Long id) {
        return itemSessaoTreinamentoRepository.findById(id);
    }

    public ItemSessaoTreinamento save(ItemSessaoTreinamento itemSessaoTreinamento) {
        return itemSessaoTreinamentoRepository.save(itemSessaoTreinamento);
    }

    public void deleteById(Long id) {
        itemSessaoTreinamentoRepository.deleteById(id);
    }
}
