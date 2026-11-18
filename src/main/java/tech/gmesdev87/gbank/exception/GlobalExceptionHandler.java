package tech.gmesdev87.gbank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ✅ Recurso não encontrado — 404
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> handleRecursoNaoEncontrado(RecursoNaoEncontradoException ex) {
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("status", HttpStatus.NOT_FOUND.value());
        resposta.put("erro", "Recurso não encontrado");
        resposta.put("mensagem", ex.getMessage());
        resposta.put("dataHora", LocalDateTime.now());
        return new ResponseEntity<>(resposta, HttpStatus.NOT_FOUND);
    }

    // ✅ E-mail duplicado — 409
    @ExceptionHandler(EmailDuplicadoException.class)
    public ResponseEntity<Map<String, Object>> handleEmailDuplicado(EmailDuplicadoException ex) {
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("status", HttpStatus.CONFLICT.value());
        resposta.put("erro", "Conflito de dados");
        resposta.put("mensagem", ex.getMessage());
        resposta.put("dataHora", LocalDateTime.now());
        return new ResponseEntity<>(resposta, HttpStatus.CONFLICT);
    }

    // ✅ Dados inválidos — 400
    @ExceptionHandler(DadosInvalidosException.class)
    public ResponseEntity<Map<String, Object>> handleDadosInvalidos(DadosInvalidosException ex) {
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("status", HttpStatus.BAD_REQUEST.value());
        resposta.put("erro", "Dados inválidos");
        resposta.put("mensagem", ex.getMessage());
        resposta.put("dataHora", LocalDateTime.now());
        return new ResponseEntity<>(resposta, HttpStatus.BAD_REQUEST);
    }

    // ✅ Validação do @Valid — 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidacao(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new HashMap<>();
        
        ex.getBindingResult().getFieldErrors().forEach(error ->
            erros.put(error.getField(), error.getDefaultMessage())
        );

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("status", HttpStatus.BAD_REQUEST.value());
        resposta.put("erro", "Erro de validação");
        resposta.put("mensagens", erros);
        resposta.put("dataHora", LocalDateTime.now());
        
        return new ResponseEntity<>(resposta, HttpStatus.BAD_REQUEST);
    }

    // ✅ Operação não permitida — 403
    @ExceptionHandler(OperacaoNaoPermitidaException.class)
    public ResponseEntity<Map<String, Object>> handleOperacaoNaoPermitida(OperacaoNaoPermitidaException ex) {
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("status", HttpStatus.FORBIDDEN.value());
        resposta.put("erro", "Operação não permitida");
        resposta.put("mensagem", ex.getMessage());
        resposta.put("dataHora", LocalDateTime.now());
        return new ResponseEntity<>(resposta, HttpStatus.FORBIDDEN);
    }

    // ✅ Erro genérico — 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleErroGenerico(Exception ex) {
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        resposta.put("erro", "Erro interno do servidor");
        resposta.put("mensagem", ex.getMessage());
        resposta.put("dataHora", LocalDateTime.now());
        return new ResponseEntity<>(resposta, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
