package br.com.dito.restaurante.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VendaProduto> vendaProdutos;

    private Double valor;

    @Enumerated(EnumType.STRING)
    private Status status;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<VendaProduto> getVendaProdutos() {
        return vendaProdutos;
    }

    public void setVendaProdutos(List<VendaProduto> vendaProdutos) {
        this.vendaProdutos = vendaProdutos;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}