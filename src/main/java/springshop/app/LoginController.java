package springshop.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springshop.domain.RoleName;
import springshop.domain.User;
import springshop.domain.repository.UserRepository;

@Controller
public class LoginController {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserRepository userRepository;
		
	@GetMapping("loginform")
	String loginForm() {
		return "login/loginform";
	}
	
	@GetMapping("createLogin")
	String createLoginGet() {
		return "login/createlogin";
	}
		
	@PostMapping("createLogin")
	@Transactional(readOnly = false)
		String createLoginPost(Model model , @ModelAttribute User user
				,@RequestParam(name =  "userId") String userId , @RequestParam(name = "password")String password) {
		user.setUserId(userId);
		user.setRoleName(RoleName.USER);
		String pass = passwordEncoder.encode(password);
		user.setPassword(pass);
		userRepository.saveAndFlush(user);		
		return "login/loginform";			
	}

}
