package br.com.acjn.geral.autenticacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class LoginController {

	@Autowired
	LoginService loginService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String sayHelloGet() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String sayHelloPost(@RequestParam String name, @RequestParam String password, ModelMap model) {
		System.out.println(name + " " + password);
		model.put("name", name);
		model.put("password", password);
		if (loginService.validateUser(name, password)) {
			return "welcome";
		}
		model.put("errorMessage", "Erro Credencial");
		return "login";
	}
}
