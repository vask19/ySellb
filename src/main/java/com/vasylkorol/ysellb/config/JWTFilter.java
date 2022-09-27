package com.vasylkorol.ysellb.config;

import com.vasylkorol.ysellb.model.User;
import com.vasylkorol.ysellb.security.CustomUserDetails;
import com.vasylkorol.ysellb.security.JWTUtil;
import com.vasylkorol.ysellb.service.CustomUserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final CustomUserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String  authHeader = request.getHeader("Authorization");

        if (authHeader != null && !authHeader.isBlank() && authHeader.startsWith("Bearer ")){
            String jwt = authHeader.substring(7);

            if (jwt.isBlank()){
                response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                        "Invalid JWT Token in Bearer Header");
            }
            else {
              try {
                  String  username = jwtUtil.validateTokenAndRetrieveClaims(jwt);
                  UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                  User user = ((CustomUserDetails) userDetails).getUser();
                  if (!user.isActive()) {
                      response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                              "user blocked");

                  }
                  else {
                      UsernamePasswordAuthenticationToken authToken =
                              new UsernamePasswordAuthenticationToken(userDetails,userDetails.getPassword(),userDetails.getAuthorities());

                      if (SecurityContextHolder.getContext().getAuthentication() == null){
                          SecurityContextHolder.getContext().setAuthentication(authToken);
                      }
                  }


              }catch (Exception exception){
                  response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                          "Invalid JWT Token");
              }

            }
        }
        filterChain.doFilter(request,response);



    }
}
