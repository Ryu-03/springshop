package springshop.app;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springshop.domain.Cart;
import springshop.domain.Item;
import springshop.domain.User;
import springshop.domain.repository.CartRepository;
import springshop.domain.repository.ItemRepository;
import springshop.domain.repository.UserRepository;
import springshop.domain.secuity.ShoppingUserDetails;

@Controller
public class TopPageController {
		
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@ModelAttribute
	Item setupItem() {
		Item item = new Item();
		return item;
	}
	
	@GetMapping("toppage")
	public String topGet(@AuthenticationPrincipal ShoppingUserDetails userDetails , Model model) {
		List<Item> list = itemRepository.findAll();
		model.addAttribute("list" , list);
		return "/toppage";
	}
	
	@GetMapping("userpage")
	public String userPageGet(@AuthenticationPrincipal ShoppingUserDetails userDetails , Model model) {
		User user = userRepository.findByUserId(userDetails.getUsername());
		model.addAttribute("user",user.getUserId());
		model.addAttribute("password" , "*********");
		return "/userpage";
	}
	
	@PostMapping("cartin")
	@Transactional(readOnly = false)
	public String topPost(@ModelAttribute Cart cart, Model model , 
			@AuthenticationPrincipal ShoppingUserDetails userDetails,
			@RequestParam("name")String name) {
		List<Cart> cartList = cartRepository.findByUserName(userDetails.getUsername());
		Iterator it = cartList.iterator();
		while(it.hasNext()) {
				if(it.next().toString().matches(name)) {
					model.addAttribute("error","登録されています");
					List<Item> list = itemRepository.findAll();
					model.addAttribute("list" , list);
					return "/toppage";
				}
		}	
		cartRepository.saveAndFlush(cart);
		List<Item> list2 = itemRepository.findAll();
		model.addAttribute("add","カートに追加しました");
		model.addAttribute("list" , list2);
		return  "/toppage";	
	}
	
	@GetMapping("cart")
	public String cartGet(@AuthenticationPrincipal ShoppingUserDetails userDetails ,Model model) {
		List<Cart> list = cartRepository.findByUserName(userDetails.getUsername());
		model.addAttribute("list" , list);
		return "/cart";
	}

}
