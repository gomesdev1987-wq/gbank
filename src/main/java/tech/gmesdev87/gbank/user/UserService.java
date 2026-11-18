package tech.gmesdev87.gbank.user;

import org.springframework.stereotype.Service;

import tech.gmesdev87.gbank.email.EmailService;
import tech.gmesdev87.gbank.exception.EmailDuplicadoException;
import tech.gmesdev87.gbank.user.dto.UserRegisterDTO;


@Service 
public class UserService {
     final UserRepository repo;
     private final EmailService emailService;

    public UserService(UserRepository repo, EmailService emailService){
        this.repo = repo;
        this.emailService = emailService;
    }

    public User create(UserRegisterDTO request) {
        if (repo.existsByEmail(request.email())) {
            throw new EmailDuplicadoException(request.email());
        }

        User user = UserRegisterDTO.ToEntity(request);
        // ✅ Enviar e-mail de boas-vindas
        String remetente = "seu-email@gmail.com";
        String assunto = "Bem-vindo ao GBank! 🎉";
        String mensagem = """
            Olá %s,
            
            Seja muito bem-vindo ao GBank! 
            Sua conta foi criada com sucesso.
            
            Atenciosamente,
            Equipe GBank
            """.formatted(request.nome());

        emailService.enviarSimples(remetente, request.email(), assunto, mensagem);

        return repo.save(user);
    }
}
