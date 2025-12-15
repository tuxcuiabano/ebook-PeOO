package br.com.dito.restaurante.controller;

import br.com.dito.restaurante.DTO.ProdutoDTO;
import br.com.dito.restaurante.service.ProdutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/produtos")
    public String listar(Model model) {
        model.addAttribute("produtos", produtoService.listarTodos());
        return "produto/lista";
    }

    @GetMapping("/produtos/cadastrar")
    public String novoProduto(Model model) {
        model.addAttribute("produto", new ProdutoDTO());
        return "produto/formCadastrar";
    }

    @PostMapping("/produtos/salvar")
    public String salvar(@ModelAttribute ProdutoDTO dto) {
        if (dto.getId() == null) {
            produtoService.adicionarProduto(dto);
        } else {
            produtoService.atualizarProduto(dto.getId(), dto);
        }
        return "redirect:/produtos";
    }

    @GetMapping("/produtos/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        ProdutoDTO dto = produtoService.buscarPorId(id);
        model.addAttribute("produto", dto);
        return "produto/formCadastrar";
    }

    @GetMapping("/produtos/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return "redirect:/produtos";
    }
}
