package it.oved96.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PrimoController {

	@RequestMapping("/hello")
	public String hello() {
		return "index";
	}
}
