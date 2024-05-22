package br.com.todo.manager.modules.login.service;

import br.com.todo.manager.model.exceptions.AuthenticationException;
import br.com.todo.manager.model.exceptions.TokenInvalidException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

public class AuthenticationService {
    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String JWT_SIGNINKEY = "signInKey";
    private static final String BEARER = "Bearer ";
    private static final String AUTHORITIES = "authorities ";
    private static final int EXPIRATIONS_TOKEN_ONE_HOUR = 3600000;
    private static final String ACCESS_CONTROL_EXPOSE_HEADERS = "Access-Control-Expose-Headers";

    static public void addJWTToken(HttpServletResponse response, Authentication authentication) {
        Map<String, Object> claims = new HashMap<>();
        List<String> authoritiesList = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        claims.put(AUTHORITIES, authoritiesList);

        String jwtToken = Jwts.builder()
            .setSubject(authentication.getName())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONS_TOKEN_ONE_HOUR))
            .signWith(SignatureAlgorithm.ES512, JWT_SIGNINKEY)
            .addClaims(claims)
            .compact();



        response.addHeader(HEADER_AUTHORIZATION, BEARER + jwtToken);
        response.addHeader(ACCESS_CONTROL_EXPOSE_HEADERS, HEADER_AUTHORIZATION);
    }

    static public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_AUTHORIZATION);
        if (token != null) {
            Claims user = Jwts.parser()
                .setSigningKey(JWT_SIGNINKEY)
                .parseClaimsJws(token.replace(BEARER, ""))
                .getBody();

            if (user != null) {
                List<SimpleGrantedAuthority> permissions = ((ArrayList<String>)user.get(AUTHORITIES))
                        .stream()
                        .map(SimpleGrantedAuthority::new)
                        .toList();
                return new UsernamePasswordAuthenticationToken(user, null, permissions);
            } else {
                throw new AuthenticationException("authentication.failed", HttpStatus.FORBIDDEN);
            }
        }
        throw new TokenInvalidException("token.invalid.failed", HttpStatus.FORBIDDEN);
    }

}
