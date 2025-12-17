package br.com.dito.restaurante.service;

import br.com.dito.restaurante.DTO.VendaDTO;
import br.com.dito.restaurante.DTO.VendaProdutoDTO;
import br.com.dito.restaurante.model.Produto;
import br.com.dito.restaurante.model.Status;
import br.com.dito.restaurante.model.Venda;
import br.com.dito.restaurante.model.VendaProduto;
import br.com.dito.restaurante.repository.ProdutoRepository;
import br.com.dito.restaurante.repository.VendaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public Venda registrarVenda(VendaDTO vendaDTO) {

        Venda venda = new Venda();
        venda.setStatus(Status.RECEBIDO);

        List<VendaProduto> itensVenda = new ArrayList<>();
        double valorTotal = 0.0;

        for (VendaProdutoDTO itemDTO : vendaDTO.getItens()) {

            Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            VendaProduto vendaProduto = new VendaProduto();
            vendaProduto.setVenda(venda);
            vendaProduto.setProduto(produto);
            vendaProduto.setQuantidade(itemDTO.getQuantidade());

            itensVenda.add(vendaProduto);

            valorTotal += produto.getPreco() * itemDTO.getQuantidade();
        }

        venda.setVendaProdutos(itensVenda);
        venda.setValor(valorTotal);

        return vendaRepository.save(venda);
    }
}