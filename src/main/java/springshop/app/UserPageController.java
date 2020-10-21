package springshop.app;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
public class UserPageController {
	
	@Autowired
	ItemRepository itemRepository;
		
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CartRepository cartRepository;
	
	@GetMapping("/changepass")
	public String changeGet() {
		return "/changepass";
	}
	
	@GetMapping("/deleteuser")
	public String deleteGet(@AuthenticationPrincipal ShoppingUserDetails userDetails , Model model) {
		model.addAttribute("user",userDetails.getUsername());
		return "/deleteuser";
	}
	
	@PostMapping("/deleteuser")
	public String deletePost(Model model,
							 @AuthenticationPrincipal ShoppingUserDetails userDetails ,
							 @RequestParam("password")String password) {
		if(encoder.matches(password , userDetails.getPassword())) {
			List<Cart> deleteList =  cartRepository.findByUserName(userDetails.getUsername());
			Iterator<Cart> it = deleteList.iterator();
			while(it.hasNext()) {
				cartRepository.deleteById(it.next().getNumber());
			}
			userRepository.deleteById(userDetails.getUsername());
			return "redirect:/logout";
		}else {
			model.addAttribute("comment" , "パスワードが違います");
		return ("/deleteuser");
		}
	}
	
	@PostMapping("/enterpass")
	public String chengePost(Model model,
							 @RequestParam("oldpass") String oldPass,
							 @RequestParam("newpass1") String newPass1,
							 @RequestParam("newpass2") String newPass2,
							 @AuthenticationPrincipal ShoppingUserDetails userDetails) {		
		if(encoder.matches(oldPass, userDetails.getPassword())) {
			if(newPass1.equals(newPass2)) {
				String newPassword = encoder.encode(newPass1);
				userDetails.getUser().setPassword(newPassword);
				userRepository.saveAndFlush(userDetails.getUser());
				User user = userRepository.findByUserId(userDetails.getUsername());
				model.addAttribute("user",user.getUserId());
				model.addAttribute("password" , "*********");
				model.addAttribute("comment","変更されました。");
				return "/userpage";
			}else {
				model.addAttribute("error", "新しいパスワードは同じものを入力してください");
				return "/changepass";
			}
		}else {
			model.addAttribute("error", "現在のパスワードが違っています");
			return "/changepass";
		}
				
	}
}
