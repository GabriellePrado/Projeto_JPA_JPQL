package br.com.financas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.financas.modelo.Conta;
import br.com.financas.modelo.TipoMovimentacao;

public class MovimentacaoDao {


		
		private EntityManager em;
		
	public MovimentacaoDao(EntityManager em) {
			this.em = em;
		}



	public List<Double> getMediasPorDia(TipoMovimentacao saida, Conta conta) {
		/*sum - soma = na class é um bogDecimal
		 * avg - media = res=torna um double por padrão
		 * 
		*/
		String jpql =  "select avg(m.valor) from Movimentacao m where m.conta = :pConta and m.tipo = :pTipo group by day(m.data), month(m.data), year(m.data)";

		TypedQuery<Double> query = em.createQuery(jpql, Double.class);
		
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.SAIDA);
		
		/* Se deixar essa linha de codigo o compilador vai dar um alerta para que o programador saiba que  o compilador
		 * nao reconhece mas aceita o tipo Double para isso se faz um TypedQuery
		 * List<Double> medias = (List<Double>) query.getResultList();
		 */
		List<Double> medias = (List<Double>) query.getResultList();
		

		return query.getResultList();
	}
}
