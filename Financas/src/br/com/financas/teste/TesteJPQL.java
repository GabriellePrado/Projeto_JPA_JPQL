package br.com.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.financas.modelo.Conta;
import br.com.financas.modelo.Movimentacao;
import br.com.financas.modelo.TipoMovimentacao;
import br.com.financas.util.JPAutil;

public class TesteJPQL {

	public static void main(String[] args) {
		
		EntityManager em = new JPAutil().getEntityManager();
		em.getTransaction().begin();
		
		Conta conta = new Conta();
		conta.setId(2);
		
		String jpql = "select m from Movimentacao m where m.conta = :pConta and m.tipo = :pTipo";

		Query query = em.createQuery(jpql);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.ENTRADA);
		
		 List<Movimentacao> resultados = query.getResultList();
		 
		 for (Movimentacao movimentacao : resultados) {
			 System.out.println("Descricao: " + movimentacao.getDescricao());
			 System.out.println("Conta ID: " + movimentacao.getConta().getId());

		 }
		
		em.getTransaction().commit();
		em.close();
	}

}
