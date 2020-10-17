package shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import shop.domain.order.Order;
import shop.domain.products.Product;
import shop.domain.shopping.ShoppingCart;

@SpringBootApplication
public class WebShopApplication implements CommandLineRunner {
	@Autowired
	private RestOperations  restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(WebShopApplication.class, args);
	}

	@Bean
	RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		return restTemplate;
	}

	@Override
	public void run(String... args) throws Exception {
		//create products
		restTemplate.postForLocation("http://localhost:8080/product/A33/TV/250.00", null);
		restTemplate.postForLocation("http://localhost:8080/product/A34/MP3/75.00", null);
		//set stock		
		restTemplate.postForLocation("http://localhost:8080/product/stock/A33/133/A557", null);
		//get a product
		Product product = restTemplate.getForObject("http://localhost:8080/product/A33", Product.class);
		System.out.println("-----Product-------");
		product.print();
		// add products to the shoppingcart
		restTemplate.postForLocation("http://localhost:8080/cart/1/A33/3", null);
		restTemplate.postForLocation("http://localhost:8080/cart/1/A34/2", null);
		//get the shoppingcart
		ShoppingCart cart = restTemplate.getForObject("http://localhost:8080/cart/1", ShoppingCart.class);
		System.out.println("-----Shoppingcart-------");
		if (cart != null) cart.print();
		//checkout the cart
		restTemplate.postForLocation("http://localhost:8080/cart/checkout/1", null);
		//get the order
		Order order = restTemplate.getForObject("http://localhost:8080/order/1", Order.class);
		System.out.println("-----Order-------");
		if (order != null) order.print();
		
		
		
	}


}
