package com.example.listacompras.controller;

import com.example.listacompras.model.Item;
import com.example.listacompras.service.ItemServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ItemControlador {
    
    @Autowired
    private ItemServico itemServico;
    
    @GetMapping
    public String listar(Model model, @RequestParam(required = false) String busca) {
        List<Item> itens;
        
        if (busca != null && !busca.trim().isEmpty()) {
            itens = itemServico.buscarPorNome(busca);
        } else {
            itens = itemServico.listarTodos();
        }
        
        model.addAttribute("itens", itens);
        model.addAttribute("novoItem", new Item());
        model.addAttribute("busca", busca);
        model.addAttribute("totalNaoComprados", itemServico.contarItensNaoComprados());
        model.addAttribute("totalComprados", itemServico.contarItensComprados());
        
        return "lista";
    }
    
    @PostMapping("/adicionar")
    public String adicionar(@ModelAttribute Item item, RedirectAttributes redirectAttributes) {
        try {
            if (item.getNome() == null || item.getNome().trim().isEmpty()) {
                redirectAttributes.addFlashAttribute("erro", "Nome do item é obrigatório");
                return "redirect:/";
            }
            
            if (item.getQuantidade() == null || item.getQuantidade() < 1) {
                item.setQuantidade(1);
            }
            
            itemServico.salvar(item);
            redirectAttributes.addFlashAttribute("sucesso", "Item adicionado com sucesso");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao adicionar item: " + e.getMessage());
        }
        
        return "redirect:/";
    }
    
    @PostMapping("/marcar/{id}")
    public String marcarComprado(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            itemServico.marcarComoComprado(id);
            redirectAttributes.addFlashAttribute("sucesso", "Status do item atualizado");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao atualizar item: " + e.getMessage());
        }
        
        return "redirect:/";
    }
    
    @PostMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            itemServico.deletar(id);
            redirectAttributes.addFlashAttribute("sucesso", "Item removido com sucesso");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao remover item: " + e.getMessage());
        }
        
        return "redirect:/";
    }
    
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Item> itemOpt = itemServico.buscarPorId(id);
        
        if (itemOpt.isPresent()) {
            model.addAttribute("item", itemOpt.get());
            return "editar";
        } else {
            redirectAttributes.addFlashAttribute("erro", "Item não encontrado");
            return "redirect:/";
        }
    }
    
    @PostMapping("/editar/{id}")
    public String editar(@PathVariable Long id, @ModelAttribute Item item, RedirectAttributes redirectAttributes) {
        try {
            Optional<Item> itemExistente = itemServico.buscarPorId(id);
            
            if (itemExistente.isPresent()) {
                Item itemAtualizado = itemExistente.get();
                itemAtualizado.setNome(item.getNome());
                itemAtualizado.setCategoria(item.getCategoria());
                itemAtualizado.setQuantidade(item.getQuantidade());
                itemAtualizado.setObservacoes(item.getObservacoes());
                
                itemServico.atualizar(itemAtualizado);
                redirectAttributes.addFlashAttribute("sucesso", "Item atualizado com sucesso");
            } else {
                redirectAttributes.addFlashAttribute("erro", "Item não encontrado");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao atualizar item: " + e.getMessage());
        }
        
        return "redirect:/";
    }
}