package com.example.listacompras.repository;

import com.example.listacompras.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepositorio extends JpaRepository<Item, Long> {
    
    List<Item> findByCompradoOrderByIdDesc(Boolean comprado);
    
    List<Item> findByCategoriaContainingIgnoreCaseOrderByNome(String categoria);
    
    List<Item> findByNomeContainingIgnoreCaseOrderByIdDesc(String nome);
    
    @Query("SELECT i FROM Item i ORDER BY i.comprado ASC, i.id DESC")
    List<Item> findAllOrderByCompradoAndId();
    
    Long countByComprado(Boolean comprado);
}