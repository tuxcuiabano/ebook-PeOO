package br.com.dito.restaurante.controller;

import br.com.dito.restaurante.DTO.VendaDTO;
import br.com.dito.restaurante.DTO.VendaProdutoDTO;
import br.com.dito.restaurante.service.ProdutoService;
import br.com.dito.restaurante.service.VendaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @Autowired
    private ProdutoService produtoService;


    @GetMapping("/vendas")
    public String listar(Model model) {
        model.addAttribute("vendas", vendaService.listarTodas());
        return "venda/lista";
    }

    // A TELA DE CADASTRO (A COMANDA)
    @GetMapping("/vendas/nova")
    public String novaVenda(Model model) {
        VendaDTO vendaDTO = new VendaDTO();
        vendaDTO.setItens(new ArrayList<>());
        vendaDTO.getItens().add(new VendaProdutoDTO()); // Cria linha vazia p/ o form

        model.addAttribute("venda", vendaDTO);
        model.addAttribute("produtos", produtoService.listarTodos()); // Para o <select>
        return "venda/cadastro";
    }

    // RECEBER O PEDIDO
    @PostMapping("/vendas/salvar")
    public String salvar(@Valid @ModelAttribute("venda") VendaDTO venda,
                         BindingResult result, // O resultado da validação vem aqui
                         Model model) {

        if (result.hasErrors()) {
            // Se tiver erro, volta para o formulário mostrando os problemas
            model.addAttribute("produtos", produtoService.listarTodos());
            return "venda/cadastro";
        }

        vendaService.registrarVenda(venda);
        return "redirect:/vendas";
    }
}
