package it.objectway.hibernate.hr.web.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorldController {

	@RequestMapping(method = RequestMethod.GET, value = "/hello")
	public ModelAndView printHello() {
		System.out.println("Hello Spring MVC Framework!");

		// return "hello";

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("message", "hello World");

		return new ModelAndView("hello", model);
	}

}
