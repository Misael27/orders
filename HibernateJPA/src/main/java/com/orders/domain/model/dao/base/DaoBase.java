/**
 * 
 */
package com.orders.domain.model.dao.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

@Transactional
public abstract class DaoBase<K extends Serializable, E> implements DaoInterface<K, E> {

	private final Class<E> entityClass;

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public DaoBase() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
	}

	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public E findById(final Serializable id) {
		return (E) getSession().get(this.entityClass, id);
	}

	@Override
	public Serializable save(E entity) {
		return getSession().save(entity);
	}
	
	@Override
	public void update(E entity) {
		getSession().update(entity);
	}

	@Override
	public void delete(E entity) {
		getSession().delete(entity);
	}

	@Override
	public List<E> findAll() {

		// Create CriteriaBuilder
		CriteriaBuilder builder = getSession().getCriteriaBuilder();

		// Create CriteriaQuery
		CriteriaQuery<E> criteria = builder.createQuery(this.entityClass);

		criteria.from(this.entityClass);

		return getSession().createQuery(criteria).list();

	}

	@Override
	public void clear() {
		getSession().clear();

	}

	@Override
	public void flush() {
		getSession().flush();

	}

}
