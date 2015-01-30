package it.objectway.hibernate.hr.web.controllers;

import it.objectway.hibernate.hr.services.LoginService;
import it.objectway.hibernate.hr.web.forms.LoginForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;

	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public ModelAndView main() {
		LoginForm model = new LoginForm();
		return new ModelAndView("login", "model", model);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public ModelAndView login(@ModelAttribute("model") LoginForm model) {
		boolean result = loginService.getLogin(model.getUsername(), model.getPassword());
		if (!result) {
			return main();
		}
		return new ModelAndView("redirect:/hello");
	}

}
