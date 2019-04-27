package com.orders.domain.model.dao;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.orders.domain.model.dao.base.DaoBase;
import com.orders.domain.model.dao.interfaces.ProductDaoInterface;
import com.orders.domain.model.entity.Product;


@Repository(value="productDao")
public class ProductDaoImpl extends DaoBase<Integer, Product> implements ProductDaoInterface{

	@Override
	public Product findBySku(String sku) {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> root = cq.from(Product.class);
		cq.select(root).where(cb.equal(root.get("sku"), sku));
		Query<Product> q= getSession().createQuery(cq);
		try {
			return q.getSingleResult();
		}
		catch (NoResultException ex) {
			return null;
		}
	}

	
}
