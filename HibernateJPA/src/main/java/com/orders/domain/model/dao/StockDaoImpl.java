package com.orders.domain.model.dao;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.orders.domain.model.dao.base.DaoBase;
import com.orders.domain.model.dao.interfaces.StockDaoInterface;
import com.orders.domain.model.entity.Stock;


@Repository(value="stockDao")
public class StockDaoImpl extends DaoBase<Integer, Stock> implements StockDaoInterface{
	
	public Stock findByProduct(int productId) {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<Stock> cq = cb.createQuery(Stock.class);
        Root<Stock> root = cq.from(Stock.class);
		cq.select(root).where(cb.equal(root.join("product").get("productId"), productId));
		Query<Stock> q= getSession().createQuery(cq);
		try {
			return q.getSingleResult();
		}
		catch (NoResultException ex) {
			return null;
		}
	}

}
