package sa;

import java.time.LocalDateTime;

import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import sa.model.Customer;
import sa.model.Stock;

@SpringBootApplication
public class Sa11WebfluxclientApplication {

	public static void main(String[] args) throws InterruptedException {
//		SpringApplication.run(Sa11WebfluxclientApplication.class, args);
		Flux<Customer> customers = WebClient.create("http://localhost:8080/customers")
				.get().retrieve().bodyToFlux(Customer.class);
		customers.subscribe(s -> {
			System.out.print(LocalDateTime.now() + " : ");
			System.out.println(s);
		});
		
		Thread.sleep(15000);
		
		Flux<Stock> stocks = WebClient.create("http://localhost:8080/stocks")
				.get().retrieve().bodyToFlux(Stock.class);
		stocks.subscribe(s -> {
			System.out.print(LocalDateTime.now() + " : ");
			System.out.println(s);
		});
		Thread.sleep(25000);
	}

}
