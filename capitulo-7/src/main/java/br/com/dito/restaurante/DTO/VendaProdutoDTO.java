package br.com.dito.restaurante.DTO;

import java.util.List;

public class VendaDTO {

    private List<VendaProdutoDTO> itens;

    public List<VendaProdutoDTO> getItens() {
        return itens;
    }

    public void setItens(List<VendaProdutoDTO> itens) {
        this.itens = itens;
    }
}