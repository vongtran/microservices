package shop.products.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;


@Service
public class ProductCatalogService {
	@Autowired
	private RestOperations  restTemplate;
	
//	@Bean
//	RestTemplate restTemplate() {
//		RestTemplate restTemplate = new RestTemplate();
//		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
//		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
//		return restTemplate;
//	}

	public ProductDTO getProduct(String productnumber) {
		return restTemplate.getForObject("http://localhost:8082/product/" + productnumber, ProductDTO.class);
	}
	
}
