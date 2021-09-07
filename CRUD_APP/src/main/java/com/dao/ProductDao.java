package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.model.Product;

@Component
public class ProductDao {

	@Autowired
	private HibernateTemplate template;

	@Transactional
	public void createProduct(Product product) {

		this.template.saveOrUpdate(product);
	}

//	for all product

	public List<Product> getProducts() {
		List<Product> prod = this.template.loadAll(Product.class);
		return prod;
	}

//	get the single product

	public Product getProduct(int pid) {

		return this.template.get(Product.class, pid);

	}

//	delete the single product
	
	@Transactional
	public void deleteProduct(int pid) {

		Product p = this.template.load(Product.class, pid);
		this.template.delete(p);
	}

}
