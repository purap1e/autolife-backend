package kz.auto_life.stoservice.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.auto_life.stoservice.exceptions.UnauthorizedException;
import kz.auto_life.stoservice.payload.ResponseMessage;
import kz.auto_life.stoservice.properties.JwtProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    private final JwtProperties jwtProperties;
    private final ServletContext servletContext;
    private static final int PIECE_NAME = 2;
    private static final int SUBSTRING_VALUE = 8;

    public CustomAuthorizationFilter(JwtProperties jwtProperties, ServletContext servletContext) {
        this.jwtProperties = jwtProperties;
        this.servletContext = servletContext;
    }

    public String getName(DecodedJWT decodedJWT) {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(decodedJWT.getPayload()));
        String[] pieces = payload.split(",");
        System.out.println(pieces[PIECE_NAME]);
        return pieces[PIECE_NAME].substring(SUBSTRING_VALUE, pieces[PIECE_NAME].length() - 1);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        try {
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                String token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256(jwtProperties.getSecret().getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(token);
                String userId = decodedJWT.getSubject();
                servletContext.setAttribute("userId", userId);
                servletContext.setAttribute("name", getName(decodedJWT));
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, null, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                filterChain.doFilter(request, response);
            } else if (authorizationHeader != null && authorizationHeader.startsWith("Basic ")) {
                String token = authorizationHeader.substring("Basic ".length());
                if (!token.equals("YWRtaW46cGFzc3dvcmQ=")) {
                    throw new UnauthorizedException(new ResponseMessage("Invalid credentials"));
                }
                filterChain.doFilter(request, response);
            } else {
                filterChain.doFilter(request, response);
            }
        } catch (Exception exception) {
            log.error("Error logging in: {}", exception.getMessage());
            response.setHeader("error", exception.getMessage());
            response.setStatus(FORBIDDEN.value());
            Map<String, String> error = new HashMap<>();
            error.put("error_message", exception.getMessage());
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        }
    }
}
