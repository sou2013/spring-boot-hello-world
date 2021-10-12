package com.example.hello.controller;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
		@RequestMapping(value = "/api/add", method = { RequestMethod.OPTIONS, RequestMethod.GET })
		public String doAdd(HttpServletRequest req, HttpServletResponse resp, Model model) {
			//resp.setHeader("Access-Control-Allow-Origin", "https://localhost:8443");
			resp.setHeader("Access-Control-Allow-Origin", "*");
			resp.setHeader( "Access-Control-Allow-Headers", "Content-Type, X-Requested-With , accept,Origin, Access-Control-Request-Method,Access-Control-Request-Headers, Content-Range, Content-Disposition, Content-Description");
			resp.setHeader("Access-Control-Allow-Methods" ,"GET, POST, PUT, DELETE, OPTIONS");

			Object c = req.getAttribute("claims");
			String result = "{\"result\":\"OK\", \"ts\":\"" + System.currentTimeMillis() + "\" }";
			String role = "";
			if(c != null) {
				Claims claims = (Claims) c;
				Object r = claims.get("roles");
				if(r != null) {
					role = r.toString();
				}
				System.out.println("role " + r.toString());
			}else {
				//resp.setStatus(401);
			}
			if(! role.contains("operator") && ! role.contains("supervisor")) {
				result =  "{\"result\":\"Error\"}";;
			}
			return result;
		}


}