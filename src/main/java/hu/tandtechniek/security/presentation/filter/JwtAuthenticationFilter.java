package hu.tandtechniek.security.presentation.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.tandtechniek.security.data.User;
import hu.tandtechniek.security.presentation.DTO.AuthDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.CrossOrigin;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final String secret;
    private final Integer expirationInMs;

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(
            String path,
            String secret,
            Integer expirationInMs,
            AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(path));

        this.secret = secret;
        this.expirationInMs = expirationInMs;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws IOException, RuntimeException {

        String ip = getClientIP(request);


        AuthDTO login = new ObjectMapper()
                .readValue(request.getInputStream(), AuthDTO.class);

        Authentication authentication;
        try{
            authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.username, login.password));
        }catch (Exception e){
            throw e;
        }
        return authentication;
    }

    private String getClientIP(HttpServletRequest request) {
        return request.getRemoteAddr();
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain filterChain, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        List<String> roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        byte[] signingKey = this.secret.getBytes();

        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, Keys.hmacShaKeyFor(signingKey))
                .setHeaderParam("typ", "JWT")
                .setIssuer("tandtechniek-api")
                .setAudience("tandtechniek")
                .setSubject(user.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + this.expirationInMs))
                .claim("rol", roles)
                .compact();

        response.addHeader("Authorization", "Bearer " + token);
    }
}
