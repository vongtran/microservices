package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
	@Autowired
	AccountFeignClient accountClient;
	@RequestMapping("/customer/{dustomerid}")
	public Account getName(@PathVariable("customerid") String customerid) {
		Account account = accountClient.getName(customerid);
		return account;
	}
	
	@FeignClient("AccountService")
	interface AccountFeignClient {
		@RequestMapping("/account/{customerid}")
		public Account getName(@PathVariable("customerid") String customerid);
	}
}
