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

       chain.doFilter(request, response);
    }

    // other methods
}