package com.example.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

//@Controller
@RestController
public class HelloController {


		@GetMapping("/hello")
		public String getHello(HttpServletResponse resp,  Model model) {
			resp.setHeader("Access-Control-Allow-Origin", "https://localhost:8443");
		model.addAttribute("hello","Hello Spring Boot User");
		//return "hello";
		return "{\"a\":\"b\"}";
		}

//	@GetMapping("/api/add")
		@CrossOrigin(origins = "*" , maxAge = 10000)
//		@CrossOrigin()
		@RequestMapping(value = "/api/add", method = { RequestMethod.OPTIONS, RequestMethod.GET })
		public String doAdd(HttpServletResponse resp,  Model model) {
			//resp.setHeader("Access-Control-Allow-Origin", "https://localhost:8443");
			resp.setHeader("Access-Control-Allow-Origin", "*");

			resp.setHeader( "Access-Control-Allow-Headers", "Content-Type, X-Requested-With , accept,Origin, Access-Control-Request-Method,Access-Control-Request-Headers, Content-Range, Content-Disposition, Content-Description");
			resp.setHeader("Access-Control-Allow-Methods" ,"GET, POST, PUT, DELETE, OPTIONS");
			model.addAttribute("hello","Hello Spring Boot User");
			//return "hello";
			return "{\"result\":\"OK\", \"ts\":\"" + System.currentTimeMillis() + "\" }";
		}
}