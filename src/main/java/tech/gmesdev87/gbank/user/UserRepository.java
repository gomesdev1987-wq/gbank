package tech.gmesdev87.gbank.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Já vem com métodos prontos: save(), findAll(), findById(), delete() etc.
    Boolean existsByEmail(String email);
}
