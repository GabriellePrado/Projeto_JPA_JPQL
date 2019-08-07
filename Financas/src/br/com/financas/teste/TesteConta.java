package br.com.financas.teste;

import javax.persistence.EntityManager;

import br.com.financas.modelo.Conta;
import br.com.financas.util.JPAutil;

public class TesteConta {

	public static void main(String[] args) {

		Conta conta = new Conta();
		conta.setTitular("Gabrielle");
		conta.setAgencia("2222");
		conta.setBanco("Santander");
		conta.setNumero("874");

		EntityManager em = new JPAutil().getEntityManager();

		// com o begin inicia a transação
		em.getTransaction().begin();

		conta = em.find(Conta.class, 1);
		em.remove(conta);

		em.getTransaction().commit();
		// e no commit finaliza
		em.close();
	}

}
