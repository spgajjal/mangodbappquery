package com.capgemini.productapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.productapp.entity.Product;
import com.capgemini.productapp.exception.ProductNotFoundException;
import com.capgemini.productapp.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/product")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		ResponseEntity<Product> responseEntity = 
				new ResponseEntity<Product>(productService.addProduct(product), HttpStatus.OK);
		
		return responseEntity;
	}
	
	@PutMapping("/product")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
		try {
			Product productFromDb = 
					productService.findProductById(product.getProductId());
			if(productFromDb != null)
				return new ResponseEntity<Product>(productService.updateProduct(product), HttpStatus.OK);
		}
		catch(ProductNotFoundException exception) {
			//logged the exception
		}		
		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/products/{productId}")
	public ResponseEntity<Product> findProductById(@PathVariable int productId) {
		try {
			Product productFromDb = 
					productService.findProductById(productId);
			return new ResponseEntity<Product>(productFromDb, HttpStatus.OK);
		}
		catch(ProductNotFoundException exception) {
			//logged the exception
		}
		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}
	
	
	
	@DeleteMapping("/products/{productId}")
	public ResponseEntity<Product> deleteProduct(@PathVariable int productId) {
		try {
			Product productFromDb = 
					productService.findProductById(productId);
			if(productFromDb != null) {
				productService.deleteProduct(productFromDb);
				return new ResponseEntity<Product>(HttpStatus.OK);
			}
		}
		catch(ProductNotFoundException exception) {
			//logged the exception
		}		
		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}
	/*
	@GetMapping("/v1/order/customer/{customerId}")
	public ResponseEntity<List<Order>> findOrderByCustomerId(@PathVariable int customerId) {
		logger.info("Get order   /v1/order/customer/" + customerId);
		return new ResponseEntity<List<Order>>(orderService.findOrderByCustomerId(customerId), HttpStatus.OK);
	}*/
	
	@GetMapping("/products/category")
	public ResponseEntity<List<Product>> findproductCategory(@RequestParam String productCategory){
		System.out.println("con");
		return new ResponseEntity<List<Product>>(productService.findproductCategory(productCategory),HttpStatus.OK);
		
	}
	/*@GetMapping("/products/category")
	public ResponseEntity<List<Product>> findProductByCategory(@RequestParam String productCategory) {
		System.out.println("con1");
		return new ResponseEntity<List<Product>>(productService.findproductCategory(productCategory), HttpStatus.OK);
	}*/

	@GetMapping("/product/{productName}")
	public ResponseEntity<List<Product>> findproductName(@PathVariable String productName){
	
		return new ResponseEntity<List<Product>>(productService.findproductName(productName),HttpStatus.OK);
		
	
	}
	
	@GetMapping("/product/{productPrice}")
	public ResponseEntity<List<Product>> findproductPrice(String ProductPrice, int lowPrice, int highPrice){
	
		return new ResponseEntity<List<Product>>(productService.findproductPrice(ProductPrice, highPrice, highPrice),HttpStatus.OK);
		
	
	}
}
