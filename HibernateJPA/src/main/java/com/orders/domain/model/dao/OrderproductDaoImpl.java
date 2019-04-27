package com.orders.domain.model.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.orders.domain.model.dao.base.DaoBase;
import com.orders.domain.model.dao.interfaces.OrderproductDaoInterface;
import com.orders.domain.model.entity.Orderproduct;
import com.orders.domain.model.entity.Stock;


@Repository(value="orderproductDao")
public class OrderproductDaoImpl extends DaoBase<Integer, Orderproduct> implements OrderproductDaoInterface{

	@Override
	public int addProductStock(Orderproduct orderproduct, Stock stock) {
		
		Transaction transObj = null;
		Session sessionObj = null;
		
		try {
			sessionObj = getSession();
			
		//	transObj = sessionObj.beginTransaction();

			sessionObj.save(orderproduct);
			
			stock.setQuanty(stock.getQuanty()-orderproduct.getQuanty());
			sessionObj.update(stock);
			
			//transObj.commit();
		}
		catch (HibernateException exObj) {
		    /*if(transObj!=null){
		        transObj.rollback();
		    }*/
		    return 0;
		} finally {
		   // sessionObj.close(); 
		}
		
		return orderproduct.getOrderProductId();
	}

	@Override
	public void deleteProductStock(Orderproduct orderproduct, Stock stock) {
		Transaction transObj = null;
		Session sessionObj = null;
		
		try {
			sessionObj = getSession();
			//transObj = sessionObj.beginTransaction();
			
			
			stock.setQuanty(stock.getQuanty()+orderproduct.getQuanty());
			sessionObj.update(stock);
			
			sessionObj.delete(orderproduct);
			
			//transObj.commit();
		}
		catch (HibernateException exObj) {
		   /* if(transObj!=null){
		        transObj.rollback();
		    }*/
			exObj.printStackTrace();
		} finally {
		    //sessionObj.close(); 
		}
	}

	@Override
	public List<Orderproduct> findOrderproduct(int orderId, int productId) {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<Orderproduct> cq = cb.createQuery(Orderproduct.class);
        Root<Orderproduct> root = cq.from(Orderproduct.class);
		cq.select(root).where(cb.or(
				cb.equal(root.join("order").get("orderId"), orderId),
				cb.equal(root.join("product").get("productId"), productId)
				));
		Query<Orderproduct> q= getSession().createQuery(cq);
		try {
			return q.list();
		}
		catch (NoResultException ex) {
			return null;
		}
	}

}
