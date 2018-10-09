package com.capgemini.productapp.repository;



import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.capgemini.productapp.entity.Product;

public interface ProductRepository extends MongoRepository<Product, Integer>{

@Query("{'productCategory':?0}")
	public List<Product> findproductCategory(String productCategory);

@Query("{'productName':?0}")
public List<Product> findproductName(String productName);
	
@Query("{'productPrice': [ {'lp':?0, 'hp':?1}}")
public List<Product> findproductPrice(String productprice,int lowprice,int highprice);


}
//@Query("{$and: [ { 'categoryId': { $eq: ?0 } }, { 'isDeleted': { $eq: ?1 } } ]}")