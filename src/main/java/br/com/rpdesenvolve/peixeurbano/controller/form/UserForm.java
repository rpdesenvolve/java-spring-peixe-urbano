package br.com.rpdesenvolve.peixeurbano.controller.form;

import br.com.rpdesenvolve.peixeurbano.modelo.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserForm {

    @NotNull @NotEmpty @Length(min = 5)
    private String name;
    @NotNull @NotEmpty
    private String email;
    @NotNull @NotEmpty
    private String userPassword;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getUserPassword() { return userPassword; }

    public void setUserPassword(String userPassword) { this.userPassword = userPassword; }

    public Usuario convert() { return new Usuario(name, email, userPassword); }
}
