package com.example.AtividadePontuada.repository;

import com.example.AtividadePontuada.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// @Repository: Indica que esta interface é um componente de acesso a dados.
// O Spring vai criar a implementação automática pra você.
@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

    // JpaRepository<ClienteModel, Long>: Aqui você tá herdando todos os métodos prontos,
    // como .save(), .findAll(), .findById() e .deleteById().
    // O 'Long' indica o tipo da Chave Primária (ID).

    //O Spring Data JPA lê o nome do metodo 'findByEmail'
    // e gera automaticamente o SQL: "SELECT * FROM tab_cliente WHERE email = ?".
    // Optional: Evita o erro de NullPointerException caso o e-mail não exista.
    Optional<ClienteModel> findByEmail(String email);
}
