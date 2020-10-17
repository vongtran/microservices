package sa.controller;

import java.time.Duration;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import sa.model.Stock;

@RestController
public class StockController {

	@GetMapping(value = "/stocks", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	Flux<Stock> getStocks() {
		Flux<Stock> stocks = Flux.just(new Stock(1L, 50),
				new Stock(1L, 45),
				new Stock(1L, 30),
				new Stock(1L, 29),
				new Stock(1L, 25)
				).delayElements(Duration.ofSeconds(5));
		return stocks;
	}
}
