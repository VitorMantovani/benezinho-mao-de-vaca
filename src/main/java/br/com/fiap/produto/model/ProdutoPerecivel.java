package br.com.fiap.produto.model;

import br.com.fiap.fornecedor.model.Fornecedor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_PRODUTO_PERECIVEL")
public class ProdutoPerecivel extends Produto {

    @Column(name = "NUM_VALIDADE")
    private Integer diasValidade;

    public ProdutoPerecivel() {
    }

    public ProdutoPerecivel(String nome, String descricao, double preco, LocalDateTime fabricacao, Fornecedor fornecedor, Integer diasValidade) {
        super(nome, descricao, preco, fabricacao, fornecedor);
        this.diasValidade = diasValidade;
    }



    public Integer getDiasValidade() {
        return diasValidade;
    }

    public ProdutoPerecivel setDiasValidade(Integer diasValidade) {
        this.diasValidade = diasValidade;
        return this;
    }


    @Override
    public String toString() {
        return "ProdutoPerecivel{" +
                "diasValidade=" + diasValidade +
                ", id=" + id +
                "} " + super.toString();
    }
}
