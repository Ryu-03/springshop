package springshop.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springshop.domain.repository.CartRepository;

@Controller
public class CartController {
	
	@Autowired
	CartRepository cartRepository;
	
	@PostMapping("/delete")
	public String cartDelete(@RequestParam("number")long number) {
		cartRepository.deleteById(number);
		return "redirect:/cart";
	}

}
