package br.com.financas.teste;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.financas.modelo.Categoria;
import br.com.financas.modelo.Conta;
import br.com.financas.modelo.Movimentacao;
import br.com.financas.modelo.TipoMovimentacao;
import br.com.financas.util.JPAutil;

public class TestesMovimentacoesComCategorias {

	
	public static void main(String[] args) {
		
		Conta conta = new Conta();
		conta.setId(2);
		
		Categoria categoria1 = new Categoria("Viagem");
		Categoria categoria2 = new Categoria("Comida");
		
		Movimentacao movimentacao1 = new Movimentacao();
		movimentacao1.setData(Calendar.getInstance()); //hoje
        movimentacao1.setDescricao("Viagem � SP");
        movimentacao1.setTipo(TipoMovimentacao.SAIDA);
        movimentacao1.setValor(new BigDecimal("100.0"));
        movimentacao1.setCategoria(Arrays.asList(categoria1, categoria2));
        movimentacao1.setConta(conta);
        
		Movimentacao movimentacao2 = new Movimentacao();
		movimentacao2.setData(Calendar.getInstance()); //hoje
        movimentacao2.setDescricao("Viagem � RJ");
        movimentacao2.setTipo(TipoMovimentacao.SAIDA);
        movimentacao2.setValor(new BigDecimal("100.0"));
		movimentacao2.setConta(conta);

		EntityManager em = new JPAutil().getEntityManager();
		em.getTransaction().begin();
		
		em.persist(categoria1);
		em.persist(categoria2);
		em.persist(movimentacao1);
		em.persist(movimentacao2);
		
		
		em.getTransaction().commit();
		em.close();
	}

}