package com.example.hello;

import io.jsonwebtoken.Claims;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.CharArrayWriter;
import java.io.IOException;

import static jdk.internal.org.jline.terminal.MouseEvent.Modifier.Control;

@Component
@Order(1)
public class JWTFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        System.out.println("Received request for : " + req.getRequestURI() + "\n");
        HttpServletResponse resp = (HttpServletResponse) response;
        //resp.setHeader("Access-Control-Allow-Origin", "https://localhost:8443");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader( "Access-Control-Allow-Headers", "Content-Type, X-Requested-With , accept,Origin, Access-Control-Request-Method,Access-Control-Request-Headers, Content-Range, Content-Disposition, Content-Description");
        resp.setHeader("Access-Control-Allow-Methods" ,"GET, POST, PUT, DELETE, OPTIONS");
        String auth  =  req.getHeader("Authorization");

        if("OPTIONS".equals(req.getMethod().toUpperCase())) {
        	System.out.println("exiting JWTFilter");
        	resp.setStatus(200);
        	return;
        }
        
//auth = "123asdfasdfasdfasdf";
        if(auth != null) {
            auth = auth.substring(7);
            System.out.println("Extracted JWT (from header) = " + auth + "\n");
            TokenHelper jwt = new TokenHelper();
            Claims c = jwt.getAllClaimsFromToken(auth);
            request.setAttribute("claims", c);
            chain.doFilter(request, response);
        }else {
            System.out.println("JWT validation failed.");
            resp.sendError(401);
        }
        System.out.println("Process completed for : " + req.getRequestURI());
    }

    // other methods
}