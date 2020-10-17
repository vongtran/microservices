package shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import shop.domain.order.Order;
import shop.domain.shopping.Product;
import shop.domain.shopping.ShoppingCart;
import shop.service.OrderService;
import shop.service.ShoppingService;

@RestController
public class OrderController {
	@Autowired
	OrderService orderService;
	
	
	@GetMapping("/order/{orderNumber}")
	public ResponseEntity<?> getCart(@PathVariable String orderNumber) {
		Order order  = orderService.getOrder(orderNumber);
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}
	
}
