package shop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.domain.order.Order;
import shop.domain.order.OrderFactory;
import shop.domain.shopping.ShoppingCart;
import shop.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	OrderRepository orderRepository;

	public Order getOrder(String orderNumber) {
		Optional<Order> optOrder = orderRepository.findById(orderNumber);
		if (optOrder.isPresent()) {
			return optOrder.get();
		} else
			return null;
	}
	
	public void createOrder(ShoppingCart cart) {		
		Order order = OrderFactory.createOrder(cart);
		orderRepository.save(order);
	}

}
