package com.orders.infraestructure;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Clase para inicializar el DispatcherServlet
 * 
 */
public class OrdersWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/**
	 * 
	 */
	public OrdersWebAppInitializer() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.support.
	 * AbstractAnnotationConfigDispatcherServletInitializer#getRootConfigClasses
	 * ()
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.support.
	 * AbstractAnnotationConfigDispatcherServletInitializer#
	 * getServletConfigClasses()
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { OrdersConfiguration.class };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.support.
	 * AbstractDispatcherServletInitializer#getServletMappings()
	 */
	@Override
	protected String[] getServletMappings() {

		return new String[] { "/*" };
	}

	@Override
	protected Filter[] getServletFilters() {
		Filter [] singleton = { new CORSFilter() };
		return singleton;
	}
}
