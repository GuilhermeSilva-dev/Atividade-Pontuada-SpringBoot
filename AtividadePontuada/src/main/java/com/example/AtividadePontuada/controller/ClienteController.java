package com.example.AtividadePontuada.controller;

import com.example.AtividadePontuada.model.ClienteModel;
import com.example.AtividadePontuada.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// Indica que essa classe vai processar requisições HTTP e retornar JSON.
@RestController
// Define o endpoint base. Toda chamada para essa entidade começa com /clientes.
@RequestMapping("/clientes")
public class ClienteController {

    // Aqui é uma injeção de dependência, ou seja, o Spring gerencia a instância do Service para você.
    @Autowired
    private ClienteService service;

    // Endpoint GET: Retorna a lista completa,e O Spring converte o List<> em um Array JSON [].
    @GetMapping
    public List<ClienteModel> listarClientes() {
        return service.listarTodos();
    }

    // Endpoint POST: Usa @RequestBody para desserializar o JSON recebido no corpo da requisição.
    @PostMapping
    public ResponseEntity<Map<String, Object>> addCliente(@RequestBody ClienteModel cliente) {
        service.salvarCliente(cliente);
        // Retorno 201 Created: Indica sucesso na criação de um novo recurso.
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("mensagem", "Cliente cadastrado com sucesso."));
    }

    // Endpoint PUT: Recebe o ID pela URL (@PathVariable) e os novos dados pelo corpo.
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> atualizarCliente(@PathVariable Long id, @RequestBody ClienteModel cliente) {
        service.atualizarCliente(id, cliente);
        // Retorno 200 OK: Padrão para atualizações bem-sucedidas.
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("mensagem", "Cliente atualizado com sucesso."));
    }

    // Endpoint DELETE: Remove o registro baseado no ID da URL.
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> excluirCliente(@PathVariable Long id){
        service.excluir(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("mensagem", "Cliente excluído com sucesso."));
    }
}