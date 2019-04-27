package com.orders.domain.model.dao;


import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.orders.domain.model.dao.base.DaoBase;
import com.orders.domain.model.dao.interfaces.CurrencyDaoInterface;
import com.orders.domain.model.entity.Currency;


@Repository(value="currencyDao")
public class CurrencyDaoImpl extends DaoBase<Integer, Currency> implements CurrencyDaoInterface{

	public Currency findByCurrencyIso(String currencyIso) {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<Currency> cq = cb.createQuery(Currency.class);
        Root<Currency> root = cq.from(Currency.class);
		cq.select(root).where(cb.equal(root.get("currencyIso"), currencyIso));
		Query<Currency> q= getSession().createQuery(cq);
		try {
			return q.getSingleResult();
		}
		catch (NoResultException ex) {
			return null;
		}
	}

	
}
