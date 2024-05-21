package br.com.todo.manager.modules;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("teste")
public class TesteApi {

    @GetMapping
    private String teste() {
        return "so sucesso!";
    }
}
