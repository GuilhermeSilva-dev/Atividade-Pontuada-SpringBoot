package com.example.AtividadePontuada.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Welcome {

    @GetMapping("/")
    public String mensagem(){
        return "Seja bem-vindo!";
    }

    @GetMapping("/dev")
    public String nomeDev() {
        return "Feito pelo Desenvolvedor: Guilherme Silva";
    }
}
