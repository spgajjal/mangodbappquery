package com.capgemini.productapp.service;

import java.util.List;

import com.capgemini.productapp.entity.Product;
import com.capgemini.productapp.exception.ProductNotFoundException;

public interface ProductService {

	public Product addProduct(Product product);

	public Product updateProduct(Product product);

	public Product findProductById(int productId) throws ProductNotFoundException;

	public void deleteProduct(Product product);
	
	public List<Product> findproductCategory(String productCategory);
	
	public List<Product> findproductName(String ProductName);
	
	public List<Product> findproductPrice(int lowprice,int highprice);

}


