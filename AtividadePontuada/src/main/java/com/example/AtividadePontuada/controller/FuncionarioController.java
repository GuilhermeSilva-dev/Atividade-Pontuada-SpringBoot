package com.example.AtividadePontuada.controller;

import com.example.AtividadePontuada.model.FuncionarioModel;
import com.example.AtividadePontuada.model.ProdutoModel;
import com.example.AtividadePontuada.service.FuncionarioService;
import com.example.AtividadePontuada.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// @RestController: Define que essa classe é o cérebro que vai lidar com o JSON dos funcionários.
@RestController
// @RequestMapping: É o endereço IP/URL onde os funcionários "moram".
@RequestMapping("/funcionarios")
public class FuncionarioController {

    // O Spring faz o "link" com o Service aqui. Sem isso, a gente teria que instanciar na mão.
    @Autowired
    private FuncionarioService service;

    // Metodo GET: Pede pro Service buscar a lista no banco e já joga na tela.
    @GetMapping
    public List<FuncionarioModel> listarFuncionarios() {
        return service.listarTodos();
    }

    // Metodo POST: Recebe o funcionário novo.
    // Lembra que o erro 500 que deu antes era aqui, porque o Service barrou o duplicado!
    @PostMapping
    public ResponseEntity<Map<String, Object>> salvarFuncionario(@RequestBody FuncionarioModel funcionario) {
        service.salvarFuncionario(funcionario);
        // Retorna 201 Created: O código de status "padrão ouro" para novos registros.
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("mensagem", "Funcionario cadastrado com sucesso"));
    }

    // Metodo PUT: O @PathVariable pega o ID que você digita lá na URL do Insomnia.
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> atualizarFuncionario(@PathVariable Long id, @RequestBody FuncionarioModel funcionario) {
        service.atualizarFuncionario(id,funcionario);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("mensagem", "Funcionario atualizado com sucesso."));
    }

    // Metodo DELETE: O comando de "expulsar" do banco de dados.
    // Se o ID for o certo, ele limpa o registro.
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> excluirFuncionario(@PathVariable Long id){
        service.excluir(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("mensagem", "Funcionario excluído com sucesso."));
    }
}