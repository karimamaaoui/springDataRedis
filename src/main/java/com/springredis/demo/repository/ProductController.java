package com.springredis.demo.repository;


	import java.util.ArrayList;
	import java.util.List;
	import java.util.Optional;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.DeleteMapping;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.PutMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RestController;

import com.springredis.demo.entity.Product;
	
	@RestController
	public class ProductController {
	    @Autowired
	    private ProductRepository productRepository;
	    @PostMapping("/addProduct")
	    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
	        Product savedProduct = productRepository.save(product);
	        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
	    }
	    
	    @GetMapping("/products")
	    public ResponseEntity<List<Product>> getProducts(){
	    	List<Product> products =new ArrayList<>();
	    	productRepository.findAll().forEach(products::add);
	    	return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
	    }
	    
	    
	    @PutMapping("/product/{id}")
	    public ResponseEntity<Product> updateProduct (@PathVariable(name= "id")String id, @RequestBody Product product){
	    
	    	Optional <Product> prod =productRepository.findById(id);
	    	if(prod.isPresent())
	    	{
	    		Product productDb =prod.get();
	    		productDb.setName(product.getName());
	    		productDb.setPrice(product.getPrice());
	    		productDb.setQte(product.getQte());
	    		
	    		Product updatedProduct =productRepository.save(productDb);
	    		return new ResponseEntity<Product>(updatedProduct,HttpStatus.CREATED);
	    		
	    	}
	    	return null;
	    }
	    
	    	    
	    @DeleteMapping("/deleteProduct/{id}")
	    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") String id) {
	    	productRepository.deleteById(id);
	        return new ResponseEntity<String>("Product with id:" + id + " deleted successfully", HttpStatus.OK);
	    }
	    
}
