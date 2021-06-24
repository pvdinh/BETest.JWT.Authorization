package com.example.BETest.service;

import com.example.BETest.object.AccountCredentials;
import com.example.BETest.repository.AccountRepository;
import com.example.BETest.response.ResponseError;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static java.util.Collections.emptyList;

public class TokenAuthenticationService {
    static final long EXPIRATIONTIME = 3600 * 60 * 60 * 10; // 10 days
    static final String SECRET = "ThisIsASecret";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

    public static void addAuthentication(HttpServletResponse res, String username) throws IOException {
        String jwt = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        res.setHeader("Access-Control-Max-Age", "12000");
        res.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
        res.setHeader("Access-Control-Expose-Headers", "*");
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + jwt);
        AccountCredentials accountCredentials = accountService.findAccountCredentialByUsername(username);
        ObjectMapper objectMapper = new ObjectMapper();
        res.getWriter().write(objectMapper.writeValueAsString(new ResponseError(HttpStatus.OK.value(), objectMapper.writeValueAsString(new AccountCredentials(username, "***", accountCredentials.getRole())), jwt)));
        res.getWriter().flush();
    }

    public static void failAuthentication(HttpServletResponse res) throws IOException {
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        res.setHeader("Access-Control-Max-Age", "12000");
        res.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
        res.setHeader("Access-Control-Expose-Headers", "*");
        ObjectMapper objectMapper = new ObjectMapper();
        res.getWriter().write(objectMapper.writeValueAsString(new ResponseError(HttpStatus.UNAUTHORIZED.value(), "Fail", "Fail")));
        res.getWriter().flush();
    }

    @Autowired
    static AccountService accountService;

    public static Authentication getAuthentication(HttpServletRequest request) {
        if (accountService == null) {
            ServletContext servletContext = request.getServletContext();
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            accountService = webApplicationContext.getBean(AccountService.class);
        }
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            try {
                // parse the token.
                String user = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .getBody()
                        .getSubject();
                AccountCredentials accountCredentials = accountService.findAccountCredentialByUsername(user);
                List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                grantedAuthorities.add(new GrantedAuthority() {
                    @Override
                    public String getAuthority() {
                        return accountCredentials.getRole();
                    }
                });
                return user != null && !isTokenExpired(token) ?
                        new UsernamePasswordAuthenticationToken(user, null, grantedAuthorities) :
                        null;
            } catch (ExpiredJwtException e) {
                System.out.println(" Token expired ");
            }
        }
        return null;
    }

    public static Boolean isTokenExpired(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();
        return new Date(System.currentTimeMillis()).getTime() >= claims.getExpiration().getTime();
    }

}
