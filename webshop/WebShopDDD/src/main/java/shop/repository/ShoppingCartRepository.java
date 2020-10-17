package shop.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import shop.domain.shopping.ShoppingCart;

public interface ShoppingCartRepository extends MongoRepository<ShoppingCart, String> {

}
