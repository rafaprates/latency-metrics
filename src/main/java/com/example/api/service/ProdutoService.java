package com.example.api.service;

import com.example.api.model.Produto;
import com.example.api.repository.ProdutoRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public String criarProdutoAleatorio() {
        String nomeAleatorio = UUID.randomUUID().toString();

        Optional<Produto> produto = buscarProdutoPorNome(nomeAleatorio);

        if (produto.isPresent()) {
            throw new EntityExistsException("Produto já existe");
        }

        return salvarProduto(nomeAleatorio);
    }

    public Optional<Produto> buscarProdutoPorNome(String nome) {
        return produtoRepository.findByNome(nome);
    }

    public String salvarProduto(String nome) {
        Produto produto = new Produto(nome);
        return produtoRepository.save(produto).getNome();
    }
}
