package br.com.financas.teste;

import javax.persistence.EntityManager;

import br.com.financas.modelo.Conta;
import br.com.financas.util.JPAutil;

public class TesteBuscaConta {
	public static void main(String[] args) {

		EntityManager em = new JPAutil().getEntityManager();
		em.getTransaction().begin();

		Conta conta = em.find(Conta.class, 1);
		conta.setTitular("Marylin");
		conta.setNumero("753");
		//conta se encontra em *managed*
		System.out.println(conta.getTitular());

		// aqui neste commit aconteceu uma persistencia no banco de dados e atualizou os
		// dados
		em.getTransaction().commit();
		em.close();

		EntityManager em2 = new JPAutil().getEntityManager();
		em2.getTransaction().begin();
		//conta se encontra em *detached*  e não pode ocorrer a persistencia 
		conta.setTitular("Antonio");

		/*
		 * eu tenho meu objeto conta que ja fez a persistencia atualizando a minha
		 * tabela e que não podera fazer outro persist, portanto usamos o *merge* para
		 * atualizar a tabela
		 */
		em2.merge(conta);
		
		//a partir desse merge a conta passa a ser managed novamente podendo ocorrer a pesristencia no banco de dados 

		System.out.println(conta.getTitular());

		em2.getTransaction().commit();
		em2.close();
	}
}
