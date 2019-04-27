/**
 * 
 */
package com.orders.domain.model.dao.base;

import java.io.Serializable;
import java.util.List;

public interface DaoInterface<K extends Serializable, E> {

	/**
	 * 
	 * @param entity:
	 *            entity to save
	 * @return Identifier of saved entity
	 */
	public Serializable save(E entity);

	/**
	 * 
	 * @param entity:
	 *            entity to delete
	 */
	public void delete(E entity);


	/**
	 * Find all records
	 * 
	 * @return
	 */
	public List<E> findAll();

	/**
	 * Find by primary key
	 * 
	 * @param id
	 * @return unique entity
	 */
	public E findById(Serializable id);

	/**
	 * Clear session
	 */
	public void clear();

	/**
	 * Flush session
	 */
	public void flush();

	
	/**
	 * 
	 * @param entity:entity
	 *            to update
	 */
	void update(E entity);

}
