package com.example.hello.controller;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Controller
@RestController
public class HelloController {

		@CrossOrigin(origins = "*" , maxAge = 10000)
		@RequestMapping(value = "/api/time", method = { RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.GET })
		public String getHello(ServletRequest request, ServletResponse response, Model model) {
		model.addAttribute("hello","Hello Spring Boot User");
		//return "hello";
			HttpServletRequest req = (HttpServletRequest) request ;
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.setHeader("Access-Control-Allow-Origin", "*");
			resp.setHeader( "Access-Control-Allow-Headers", "Content-Type, X-Requested-With , accept,Origin, Access-Control-Request-Method,Access-Control-Request-Headers, Content-Range, Content-Disposition, Content-Description");
			resp.setHeader("Access-Control-Allow-Methods" ,"GET, POST, PUT, DELETE, OPTIONS");
			// Claims claims = null;

			if("OPTIONS".equals(req.getMethod().toUpperCase())) {
				System.out.println("in /api/time.  OPTIONS=ok");
				resp.setStatus(200);
				return null;
			}
			System.out.println("in /api/time.  GET=ok");
		return "{\"a\":\"b\"}";
		}

		@CrossOrigin(origins = "*" , maxAge = 10000)
		@RequestMapping(value = "/wwwapi/wwwtime", method = { RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.GET })
		public String doAdd(ServletRequest request, ServletResponse response, Model model) {
			HttpServletRequest req = (HttpServletRequest) request ;
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.setHeader("Access-Control-Allow-Origin", "*");
	        resp.setHeader( "Access-Control-Allow-Headers", "Content-Type, X-Requested-With , accept,Origin, Access-Control-Request-Method,Access-Control-Request-Headers, Content-Range, Content-Disposition, Content-Description");
	        resp.setHeader("Access-Control-Allow-Methods" ,"GET, POST, PUT, DELETE, OPTIONS");
	       // Claims claims = null;

			if("OPTIONS".equals(req.getMethod().toUpperCase())) {
	        	resp.setStatus(200);
	        	return null;
	        }
			
			Object c = req.getAttribute("claims");
			Object r = req.getAttribute("role");
			String result = "{\"result\":\"OK\", \"ts\":\"" + System.currentTimeMillis() + "\" }";
			String role = "";
	
			if(r != null) {
				role = r.toString();
			}
			System.out.println("role " + r.toString());
			
			if(! role.contains("operator") && ! role.contains("supervisor")) {
				result =  "{\"result\":\"Error\"}";;
			}
			
			System.out.println("returning: " + result);
			return result;
		}


}