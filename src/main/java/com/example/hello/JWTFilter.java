package com.example.hello;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;

//@Component
//@Order(1)
public class JWTFilter { //implements Filter {

   // @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        System.out.println("Received request for : " + req.getRequestURI() + "\n");
        HttpServletResponse resp = (HttpServletResponse) response;
        String auth  =  req.getHeader("Authorization");
        Claims claims = null;
        String role = "";
        String result = "";
        
        if("OPTIONS".equals(req.getMethod().toUpperCase())) {
        	System.out.println("client requested preflight for CORS using OPTIONS.");
        	chain.doFilter(request, response);
        } else {
        	claims = this.getClaims(req);
        	long now = System.currentTimeMillis();
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
					 req.setAttribute("claims", claims);
					 req.setAttribute("role", r);	
					 chain.doFilter(request, response);
				}
				
				System.out.println("role " + r.toString());
			}else {
				resp.setStatus(401);
			}
			
	        System.out.println("Process completed for : " + req.getRequestURI());
        }
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