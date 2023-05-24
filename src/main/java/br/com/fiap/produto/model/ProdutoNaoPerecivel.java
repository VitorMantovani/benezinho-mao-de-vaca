package br.com.fiap.produto.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_PRODUTO_NAO_PERECIVEL")
@DiscriminatorValue("PNP")
public class ProdutoNaoPerecivel extends Produto {

}
