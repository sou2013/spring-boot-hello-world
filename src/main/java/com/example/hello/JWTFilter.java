package com.example.hello;

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
        System.out.println("Starting a transaction for req : " + req.getRequestURI());
        HttpServletResponse resp = (HttpServletResponse) response;
        //resp.setHeader("Access-Control-Allow-Origin", "https://localhost:8443");
        resp.setHeader("Access-Control-Allow-Origin", "*");

        resp.setHeader( "Access-Control-Allow-Headers", "Content-Type, X-Requested-With , accept,Origin, Access-Control-Request-Method,Access-Control-Request-Headers, Content-Range, Content-Disposition, Content-Description");
        resp.setHeader("Access-Control-Allow-Methods" ,"GET, POST, PUT, DELETE, OPTIONS");
        String auth  =  req.getHeader("Authorization");
        System.out.println("auth header =" + auth);
//auth = "123asdfasdfasdfasdf";
        if(auth != null) {
            auth = auth.substring(7);
            System.out.println("JWT=" + auth);
            TokenHelper jwt = new TokenHelper();
            jwt.getAllClaimsFromToken(auth);

            chain.doFilter(request, response);
        }else {
            System.out.println("www 2222");
            resp.sendError(401);
        }
        System.out.println("Committing a transaction for req : " + req.getRequestURI());
    }

    // other methods
}