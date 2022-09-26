package br.com.rpdesenvolve.peixeurbano.controller.dto;

import br.com.rpdesenvolve.peixeurbano.modelo.Usuario;
import org.springframework.data.domain.Page;

public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String userPassword;

    public UserDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.name = usuario.getName();
        this.email = usuario.getEmail();
        this.userPassword = usuario.getUsername();
    }

    public Long getId() { return id; }

    public String getName() { return name; }

    public String getEmail() { return email; }

    public String getUserPassword() { return userPassword; }

    public static Page<UserDTO> convert(Page<Usuario> users) { return users.map(UserDTO::new); }
}
