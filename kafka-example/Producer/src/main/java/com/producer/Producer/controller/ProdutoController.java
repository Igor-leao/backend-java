package com.producer.Producer.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.producer.Producer.TopicProducer;
import com.producer.Producer.model.Produto;
import com.producer.Producer.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	private final ProdutoRepository produtoRepository;
	private final TopicProducer topicProducer;

	public ProdutoController(ProdutoRepository produtoRepository, TopicProducer topicProducer) {
		this.produtoRepository = produtoRepository;
		this.topicProducer = topicProducer;
	}
	
	@PostMapping
	public Produto salvarProduto(@RequestBody Produto produto) {
		Produto produtoSalvo = produtoRepository.save(produto);
		topicProducer.send(produtoSalvo.toString());
		return produtoSalvo;
	}
	
	@GetMapping
	public List<Produto> buscarProdutos(){
		return produtoRepository.findAll();
	}
}
