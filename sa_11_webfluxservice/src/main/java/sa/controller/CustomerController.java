package sa.controller;

import java.time.Duration;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import sa.model.Customer;

@RestController
public class CustomerController {

	@GetMapping(value = "/customers", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Customer> getAllCustomers() {
		Flux<Customer> customers = Flux.just(
				new Customer(1L, "Walter:", "White", 29),
				new Customer(2L, "Skyler", "White", 24),
				new Customer(3L, "Saul", "Goodman", 27),
				new Customer(4L, "Jesse", "Pinkman", 24)
				).delayElements(Duration.ofSeconds(3));
		return customers;
	}
	
	
}
