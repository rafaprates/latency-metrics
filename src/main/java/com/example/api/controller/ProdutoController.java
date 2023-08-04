package com.example.api.controller;

import com.example.api.service.ProdutoService;
import io.micrometer.core.annotation.Timed;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @Timed(value = "latencia.produtos")
    @PostMapping("/produtos")
    public ResponseEntity<String> criarProdutoAleatorio() {
        String nomeProduto = produtoService.criarProdutoAleatorio();
        return ResponseEntity.status(201).body(nomeProduto);
    }
}
