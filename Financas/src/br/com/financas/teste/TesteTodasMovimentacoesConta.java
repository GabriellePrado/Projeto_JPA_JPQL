package br.com.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.financas.modelo.Conta;
import br.com.financas.util.JPAutil;

public class TesteTodasMovimentacoesConta {

	public static void main(String[] args) {

		EntityManager em = new JPAutil().getEntityManager();
		em.getTransaction().begin();
		//o DISTINCT traz todos os resultados sem repetições do titular - ao usar dizemos ao banco que queremos apenas os resultados diferentes
		String jpql = "select distinct c from Conta c join fetch c.movimentacoes";
		/* com essa query ele ira trazer somente os titulares que contem movimentacoes
		 * para trazer tambem os que nao contem movimentações devemos usar 'left join fetch'*/
		Query query = em .createQuery(jpql);
		
		List<Conta> todasContas = query.getResultList();
		
		for (Conta conta : todasContas) {
			System.out.println("Titular "  + conta.getTitular());
			System.out.println("Movimentacoes : " +  conta.getMovimentacoes());
			/*
			 * esse tipo de relacionamento se chama Lazy, o banco só vai atras dele
			 * quando realmente precisa  isso porque  a query está assim: select c from Conta c
			 */
		}
		
	}

}
