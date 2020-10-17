package shop.order.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import shop.shopping.service.ShoppingCartDTO;


@Service
public class OrderService {
	@Autowired
	private RestOperations  restTemplate;
	
	@Bean
	RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		return restTemplate;
	}
	
	public void createOrder(ShoppingCartDTO shoppingCartDTO) {	
		restTemplate.postForLocation("http://localhost:8084/cart/checkout/" + shoppingCartDTO.getCartid(), null);
	}
	
}
