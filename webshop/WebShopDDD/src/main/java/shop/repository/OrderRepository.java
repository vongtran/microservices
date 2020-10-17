package shop.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import shop.domain.order.Order;


public interface OrderRepository extends MongoRepository<Order, String> {

}
