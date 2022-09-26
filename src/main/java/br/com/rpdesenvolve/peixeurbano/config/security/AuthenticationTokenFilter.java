package br.com.rpdesenvolve.peixeurbano.config.security;

import br.com.rpdesenvolve.peixeurbano.modelo.Usuario;
import br.com.rpdesenvolve.peixeurbano.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationTokenFilter extends OncePerRequestFilter {

    private TokenJwtService tokenJwtService;
    private UserRepository userRepository;

    public AuthenticationTokenFilter(TokenJwtService tokenJwtService, UserRepository userRepository) {
        this.tokenJwtService = tokenJwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recoverToken(request);
        boolean isValid = tokenJwtService.isValidToken(token);

        if (isValid) {
            clientAuthentication(token);
        }

        filterChain.doFilter(request, response);
    }

    private void clientAuthentication(String token) {
        Long userId = tokenJwtService.getUserId(token);
        Usuario user = userRepository.findById(userId).get();

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recoverToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7, token.length());
    }
}
