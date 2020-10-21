package springshop.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import springshop.domain.Item;
import springshop.domain.repository.ItemRepository;

@Controller
public class RegisterController {
	
	@Autowired
	ItemRepository repository;
	
	@ModelAttribute
	Item setupItem() {
		Item item = new Item();
		return item;
	}
	
	@GetMapping("/register")
	public String getRegister() {
		return "/register";
	}
	
	@PostMapping("/create")
	@Transactional(readOnly=false)
	public String PostRegister(@ModelAttribute Item item ,Model model) {
		repository.saveAndFlush(item);
		model.addAttribute("comment" , "登録しました	");
		return "/register";
	}

}
