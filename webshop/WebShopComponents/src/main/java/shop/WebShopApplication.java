package shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import shop.customers.service.AddressDTO;
import shop.customers.service.CustomerDTO;
import shop.order.service.OrderDTO;
import shop.products.service.ProductDTO;
import shop.shopping.service.ShoppingCartDTO;

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
		//create customer
		CustomerDTO customerDto = new CustomerDTO("101","Frank","Brown","fBrown@Hotmail.com","123456");
		AddressDTO addressDTO = new AddressDTO("1000 N main Street", "Fairfield","52557","USA");
		customerDto.setAddress(addressDTO);
		//todo: call the customer component to add the customer
		final String serverURL = "http://localhost:8080/";
		final String customerURL = serverURL + "customer";
		HttpEntity<CustomerDTO> customerRequest = new HttpEntity<CustomerDTO>(customerDto);
		customerDto = restTemplate.postForObject(customerURL, customerRequest, CustomerDTO.class);
		// get customer
		//todo: call the customer component to get the customer with id 101 and print the result
		customerDto = restTemplate.getForObject(customerURL + "/101", CustomerDTO.class);
		//create products
		String productNo1 = "product1";
		String prodctDescription1 = "Product1";
		Double price1 = 11.0;
		Integer quantity = 1000;
		String locationcode = "IA";
		
		String productNo2 = "product2";
		String prodctDescription2 = "Product2";
		Double price2 = 22.0;
		
		String productURL1 = buildPostProductURL(productNo1, prodctDescription1, price1);
		String stockURL1 = buildPostStockURL(productNo1, quantity, locationcode);
		String productURL2 = buildPostProductURL(productNo2, prodctDescription2, price2);
		String stockURL2 = buildPostStockURL(productNo2, quantity, locationcode);
		//todo: call the product component to create the first product 
		//todo: call the product component to create the second product 
		ProductDTO product1 = restTemplate.postForObject(productURL1, null, ProductDTO.class);
		ProductDTO product2 = restTemplate.postForObject(productURL2, null, ProductDTO.class);
		//set stock	
		//todo: call the product component to set the stock for the first product
		product1 = restTemplate.postForObject(stockURL1, null, ProductDTO.class);
		product2 = restTemplate.postForObject(stockURL2, null, ProductDTO.class);
		//get a product
		//todo: call the product component to get the the first product and print the result
		product1 = restTemplate.getForObject(serverURL + "/product/" + productNo1, ProductDTO.class);
		product2 = restTemplate.getForObject(serverURL + "/product/" + productNo2, ProductDTO.class);
		// add products to the shoppingcart
		//todo: call the shopping component to add the first product to the cart
		//todo: call the shopping component to add the second product to the cart
		String addToCartProduct1 = buildPostCartURL("1", product1.getProductnumber(), 2);
		String addToCartProduct2 = buildPostCartURL("1", product2.getProductnumber(), 3);
		ShoppingCartDTO cart = restTemplate.postForObject(addToCartProduct1, null, ShoppingCartDTO.class);
		cart = restTemplate.postForObject(addToCartProduct2, null, ShoppingCartDTO.class);
		//get the shoppingcart
		//todo: call the shopping component to get the cart and print the result
		cart = restTemplate.getForObject(serverURL + "/cart/1", ShoppingCartDTO.class);
		//checkout the cart		
		//todo: call the shopping component to checkout the cart 
		cart = restTemplate.postForObject(serverURL + "/cart/checkout/1", null, ShoppingCartDTO.class);
		//get the order
		//todo: call the order component to get the order and print the result 
		OrderDTO order = restTemplate.getForObject(serverURL + "/order/1", OrderDTO.class);
		//add customer to order
		//todo: call the order component to add a customer to the order
		 order = restTemplate.postForObject(serverURL + "/order/setCustomer/"+order.getOrdernumber() 
		 	+ customerDto.getCustomerNumber(), null, OrderDTO.class);
		//confirm the order
		//todo: call the order component to confirm the order
		order = restTemplate.postForObject(serverURL + "/order/" + order.getOrdernumber(), null, OrderDTO.class);
		//get the order
		//todo: call the order component to get the order and print the result
		order = restTemplate.getForObject(serverURL + "/order" + order.getOrdernumber(), OrderDTO.class);
	}

	private String buildPostProductURL(String productnumber, String description, double price) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://localhost:8080/product/").append(productnumber).append("/")
			.append(description).append("/")
			.append(price);
		return sb.toString();
	}

	private String buildPostStockURL(String productnumber, Integer quantity, String locationcode) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://localhost:8080/product/stock/").append(productnumber).append("/")
			.append(quantity).append("/")
			.append(locationcode);
		return sb.toString();
	}

	private String buildPostCartURL(String cartId, String productnumber, Integer quantity) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://localhost:8080/cart/").append(cartId).append("/")
			.append(productnumber).append("/")
			.append(quantity);
		return sb.toString();
	}
}
