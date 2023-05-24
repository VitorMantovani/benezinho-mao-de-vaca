package br.com.fiap;

import br.com.fiap.carrinho.model.Carrinho;
import br.com.fiap.cliente.model.Cliente;
import br.com.fiap.estoque.model.Estoque;
import br.com.fiap.fornecedor.model.Fornecedor;
import br.com.fiap.pessoa.model.PessoaFisica;
import br.com.fiap.pessoa.model.Sexo;
import br.com.fiap.produto.model.ProdutoPerecivel;
import br.com.fiap.venda.model.Venda;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("oracle");
        EntityManager entityManager = factory.createEntityManager();

        PessoaFisica chicoBento = new PessoaFisica();
        chicoBento.setSexo(Sexo.MASCULINO)
                .setCPF(getCpf())
                .setNome("Francisco Bento da Silva de Souza")
                .setNascimento(LocalDate.now().minusYears(30));


        Fornecedor fazendeiro = new Fornecedor();
        fazendeiro.setPessoa(chicoBento);

        var alface = new ProdutoPerecivel();
        alface.setNome("Alface americana");
        alface.setFabricacao(LocalDateTime.now().minusDays(5));
        alface.setDescricao("Alface maravilhosa para se fazer um hamburger");
        alface.setFornecedor(fazendeiro);
        alface.setDiasValidade(10);
        alface.setPreco(5);


        var brocolis = new ProdutoPerecivel();
        brocolis.setNome("Brocolis Ninja");
        brocolis.setFabricacao(LocalDateTime.now().minusDays(7));
        brocolis.setDescricao("Este Brocolis na salada ....");
        brocolis.setFornecedor(fazendeiro);
        brocolis.setDiasValidade(10);
        brocolis.setPreco(10);

        Estoque estoque = new Estoque();
        estoque.setLocal("Armazem do Benezinho");
        estoque.addProduto(brocolis);
        estoque.addProduto(alface);

        PessoaFisica ricardo = new PessoaFisica();
        ricardo.setNome("Ricardo Sung Hoon Kim").setNascimento(LocalDate.of(2003, 4, 12));
        ricardo.setCPF(getCpf()).setSexo(Sexo.MASCULINO);

        Cliente vip = new Cliente();
        vip.setPessoa(ricardo);

        Carrinho carrinho = new Carrinho();
        carrinho.setCliente(vip);
        carrinho.addProduto(brocolis).addProduto(alface);

        Venda venda = new Venda();
        venda.setCarrinho(carrinho).setData(LocalDateTime.now());

        venda.setValor(carrinho.getValorTotal());

        entityManager.getTransaction().begin();

        entityManager.persist(chicoBento);
        entityManager.persist(fazendeiro);
        entityManager.persist(alface);
        entityManager.persist(brocolis);
        entityManager.persist(estoque);
        entityManager.persist(ricardo);
        entityManager.persist(vip);
        entityManager.persist(carrinho);
        entityManager.persist(venda);

        entityManager.getTransaction().commit();

        String id = "1";
        Venda vendaConsultada = consultarVenda(entityManager, id);
        System.out.println("Processo consultado: " + vendaConsultada);

        List<Venda> vendas = consultarTodasVendas(entityManager);
        System.out.println("Processos consultados: " + vendas);


        System.out.println(venda);

        entityManager.close();

    }

    public static Venda consultarVenda(EntityManager entityManager, String id) {
        return entityManager.find(Venda.class, id);
    }

    public static List<Venda> consultarTodasVendas(EntityManager entityManager) {
        String jpql = "SELECT v FROM Venda v";
        TypedQuery<Venda> query = entityManager.createQuery(jpql, Venda.class);
        return query.getResultList();
    }

    private static String getCpf() {
        return String.valueOf(new Random().nextInt(999999999));
    }

}