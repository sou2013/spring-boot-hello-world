package com.example.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController
public class HelloController {

	@GetMapping("/hello")
	public String getHello(Model model) {
		model.addAttribute("hello","Hello Spring Boot User");
		//return "hello";
		return "{\"a\":\"b\"}";
	}
}