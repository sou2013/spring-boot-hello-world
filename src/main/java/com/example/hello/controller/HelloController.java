package com.example.hello.controller;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.hello.TokenHelper;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

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
		@RequestMapping(value = "/api/time", method = { RequestMethod.OPTIONS, RequestMethod.GET })
		public String doAdd(ServletRequest req, ServletResponse response, Model model) {
			HttpServletRequest rq = (HttpServletRequest) req;
			HttpServletResponse resp = (HttpServletResponse) response;
			System.out.println("in /api/add. request method=" +  rq.getMethod());
		
	        resp.setHeader("Access-Control-Allow-Origin", "*");
	        resp.setHeader( "Access-Control-Allow-Headers", "Content-Type, X-Requested-With , accept,Origin, Access-Control-Request-Method,Access-Control-Request-Headers, Content-Range, Content-Disposition, Content-Description");
	        resp.setHeader("Access-Control-Allow-Methods" ,"GET, POST, PUT, DELETE, OPTIONS");
	        Claims claims = null;
	        
	        if("OPTIONS".equals(rq.getMethod().toUpperCase())) {
	        	resp.setStatus(200);
	        	return null;
	        }else {        
		       claims = getClaims(rq);
	        }
			long now = System.currentTimeMillis();
			String result = "{\"result\":\"OK\", \"ts\":\"" + now + "\" }";
			String role = "";
			if(claims != null) {
				System.out.println("expiration time: " + claims.getExpiration().getTime() );
				System.out.println("current time   : " + now );
				boolean nonExp = claims.getExpiration().getTime() > now;
				if(!nonExp) {
					System.out.println("Token expired.");
				}
				boolean valIss = claims.getIssuer().equals("RBAC-Service");
				if(!valIss) {
					System.out.println("Token issuer invalid: " + claims.getIssuer());
				}
				Object r = claims.get("roles");
				if(nonExp && valIss && r != null) {
					role = r.toString();
				}
				System.out.println("role " + r.toString());
			}else {
				//resp.setStatus(401);
			}
			if(! role.contains("operator") && ! role.contains("supervisor")) {
				result =  "{\"result\":\"Error\"}";;
			}
			System.out.println("returning: " + result);
			return result;
		}
		
		private Claims getClaims(HttpServletRequest rq) {
			Claims c = null;
			 String auth  =  rq.getHeader("Authorization");
		        //auth = "123asdfasdfasdfasdf";
		        if(auth != null) {
		            auth = auth.substring(7);
		            System.out.println("Extracted JWT (from header) = " + auth + "\n");
		            TokenHelper jwt = new TokenHelper();
		            c = jwt.getAllClaimsFromToken(auth);
		           // rq.setAttribute("claims", c);
		        }else {
		            System.out.println("Authorization header is null.");
		           
		        }
		    return c;
		}


}