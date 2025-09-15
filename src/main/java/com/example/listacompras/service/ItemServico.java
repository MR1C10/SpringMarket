package com.example.listacompras.service;

import com.example.listacompras.model.Item;
import com.example.listacompras.repository.ItemRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServico {
    
    @Autowired
    private ItemRepositorio itemRepositorio;
    
    public List<Item> listarTodos() {
        return itemRepositorio.findAllOrderByCompradoAndDataCriacao();
    }
    
    public List<Item> listarPorStatus(Boolean comprado) {
        return itemRepositorio.findByCompradoOrderByDataCriacaoDesc(comprado);
    }
    
    public List<Item> buscarPorNome(String nome) {
        return itemRepositorio.findByNomeContainingIgnoreCaseOrderByDataCriacaoDesc(nome);
    }
    
    public List<Item> listarPorCategoria(String categoria) {
        return itemRepositorio.findByCategoriaContainingIgnoreCaseOrderByNome(categoria);
    }
    
    public Optional<Item> buscarPorId(Long id) {
        return itemRepositorio.findById(id);
    }
    
    public Item salvar(Item item) {
        return itemRepositorio.save(item);
    }
    
    public Item atualizar(Item item) {
        return itemRepositorio.save(item);
    }
    
    public void deletar(Long id) {
        itemRepositorio.deleteById(id);
    }
    
    public Item marcarComoComprado(Long id) {
        Optional<Item> itemOpt = itemRepositorio.findById(id);
        if (itemOpt.isPresent()) {
            Item item = itemOpt.get();
            item.setComprado(!item.getComprado());
            return itemRepositorio.save(item);
        }
        throw new RuntimeException("Item não encontrado com ID: " + id);
    }
    
    public Long contarItensNaoComprados() {
        return itemRepositorio.countByComprado(false);
    }
    
    public Long contarItensComprados() {
        return itemRepositorio.countByComprado(true);
    }
}