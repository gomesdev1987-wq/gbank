package tech.gmesdev87.gbank.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import tech.gmesdev87.gbank.user.User;

public record UserRegisterDTO(

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
    String nome,

    @Size(max = 100, message = "O sobrenome deve ter no máximo 100 caracteres")
    String sobrenome,

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "Formato de e-mail inválido")
    @Size(max = 150, message = "O e-mail deve ter no máximo 150 caracteres")
    String email

) {
    public static User ToEntity(UserRegisterDTO request) {
        User user = new User();
        user.setNome(request.nome());
        user.setSobrenome(request.sobrenome());
        user.setEmail(request.email());
        return user;
    }
}
