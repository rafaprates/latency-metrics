package com.example.api.service;

import com.example.api.model.Produto;
import com.example.api.repository.ProdutoRepository;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.persistence.EntityExistsException;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@EnableAspectJAutoProxy
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final MeterRegistry meterRegistry;

    public ProdutoService(ProdutoRepository produtoRepository, MeterRegistry meterRegistry) {
        this.produtoRepository = produtoRepository;
        this.meterRegistry = meterRegistry;
    }

    public String criarProdutoAleatorio() {
        String nome = UUID.randomUUID().toString();

        Optional<Produto> produto = buscarProdutoPorNome(nome);

        if (produto.isPresent()) {
            throw new EntityExistsException("Produto já existe");
        }

        return salvarProduto(nome);
    }

    public Optional<Produto> buscarProdutoPorNome(String nome) {
        return produtoRepository.findByNome(nome);
    }

    public String salvarProduto(String nome) {
        Produto produto = new Produto(nome);
        Produto persistido = produtoRepository.save(produto);
        return persistido.getNome();
    }
}
