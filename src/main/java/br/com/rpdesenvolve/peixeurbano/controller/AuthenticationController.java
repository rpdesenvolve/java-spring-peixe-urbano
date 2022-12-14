package br.com.rpdesenvolve.peixeurbano.controller;

import br.com.rpdesenvolve.peixeurbano.config.security.TokenJwtService;
import br.com.rpdesenvolve.peixeurbano.controller.dto.TokenDTO;
import br.com.rpdesenvolve.peixeurbano.controller.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenJwtService tokenJwtService;

    @PostMapping
    public ResponseEntity<TokenDTO> authenticate(@RequestBody @Valid LoginForm loginForm) {
        UsernamePasswordAuthenticationToken loginData = loginForm.convert();
        try {
            Authentication authentication = authenticationManager.authenticate(loginData);
            String token = tokenJwtService.generateToken(authentication);

            return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
