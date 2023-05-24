package br.com.fiap.venda.model;

import br.com.fiap.carrinho.model.Carrinho;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "TB_VENDA"
)
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_VENDA")
    @SequenceGenerator(
            name = "SQ_VENDA",
            sequenceName = "SQ_VENDA",
            initialValue = 1,
            allocationSize = 1
    )
    @Column(name = "ID_VENDA")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_CARRINHI",
            referencedColumnName = "ID_CARRINHO",
            foreignKey = @ForeignKey(
                    name = "FK_VENDA_CARRINHO",
                    value = ConstraintMode.CONSTRAINT
            )
    )
    private Carrinho carrinho;
    @Column(name = "DT_VENDA")
    private LocalDateTime data;

    @Column(name = "NUM_VALOR")
    private double Valor;

    public Venda() {
    }

    public Venda(Long id, Carrinho carrinho, LocalDateTime data, double valor) {
        this.id = id;
        this.carrinho = carrinho;
        this.data = data;
        Valor = valor;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public Venda setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
        return this;
    }

    public LocalDateTime getData() {
        return data;
    }

    public Venda setData(LocalDateTime data) {
        this.data = data;
        return this;
    }

    public double getValor() {
        return Valor;
    }

    public Venda setValor(double valor) {
        Valor = valor;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Venda setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "id=" + id +
                ", carrinho=" + carrinho +
                ", data=" + data +
                ", Valor=" + Valor +
                '}';
    }
}
