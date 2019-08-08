package br.com.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.financas.dao.MovimentacaoDao;
import br.com.financas.modelo.Conta;
import br.com.financas.modelo.TipoMovimentacao;
import br.com.financas.util.JPAutil;

public class TestesFuncoesJPQL {

	public static void main(String[] args) {
		
		EntityManager em = new JPAutil().getEntityManager();
		em.getTransaction().begin();
		
		Conta conta = new Conta();
		conta.setId(2);
		
		TypedQuery<Double> typedQuery = em.createNamedQuery("MediasPorDia",Double.class);
		
		typedQuery.setParameter("pConta", conta);
		typedQuery.setParameter("pTipo", TipoMovimentacao.SAIDA);
		
		
		List<Double> medias = typedQuery.getResultList();
		
		for (Double media : medias) {
			System.out.println("A media é: " + media);
		}
		
		em.getTransaction().commit();
		em.close(); 
		
	}

}
