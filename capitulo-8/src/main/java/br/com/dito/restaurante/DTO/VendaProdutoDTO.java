package br.com.dito.restaurante.DTO;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class VendaProdutoDTO {

    @NotNull(message = "O prato é obrigatório!")
    private Long produtoId;

    @NotNull(message = "Informe a quantidade.")
    @Min(value = 1, message = "A quantidade deve ser pelo menos 1.")
    private Integer quantidade;

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}