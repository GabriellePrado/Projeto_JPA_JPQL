package br.com.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.financas.modelo.Categoria;
import br.com.financas.modelo.Movimentacao;
import br.com.financas.modelo.TipoMovimentacao;
import br.com.financas.util.JPAutil;

public class TesteMovimentacaoPorCategoria {

	public static void main(String[] args) {
		
			EntityManager em = new JPAutil().getEntityManager();
			em.getTransaction().begin();
			
			Categoria ct = new Categoria();
			ct.setId(2);
			
			String jpql = "select m from Movimentacao m join m.categoria c where c = :pCategoria";

			Query query = em.createQuery(jpql);
			query.setParameter("pCategoria", ct);
			
			 List<Movimentacao> resultados = query.getResultList();
			 
			 for (Movimentacao movimentacao : resultados) {
				 System.out.println("Descricao: " + movimentacao.getDescricao());
				 System.out.println("Conta ID: " + movimentacao.getConta().getId());

			 }
			
			em.getTransaction().commit();
			em.close();
	}

}
