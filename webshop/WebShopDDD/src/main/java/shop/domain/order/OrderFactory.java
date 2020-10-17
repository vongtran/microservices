package shop.domain.order;

import java.util.Date;

import shop.domain.shopping.CartLine;
import shop.domain.shopping.ShoppingCart;

public class OrderFactory {
	
	public static Order createOrder(ShoppingCart cart) {
		Order order = new Order(cart.getCartid(), new Date(),"placed");
		for (CartLine cline : cart.getCartlineList()) {
			OrderLine oline = new OrderLine();
			//create an order product from a shopping product
			Product product = new Product(cline.getProduct().getProductnumber(), cline.getProduct().getDescription(), cline.getProduct().getPrice());
			oline.setProduct(product);
			oline.setQuantity(cline.getQuantity());
			order.addOrderLine(oline);
		}
		return order;
	}
}
