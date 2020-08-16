package br.com.gm.api.resources;

import javax.management.OperationsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gm.api.model.Produto;
import br.com.gm.api.model.dto.ProdutoDTO;
import br.com.gm.api.repository.ProdutoRepository;
import br.com.gm.api.services.ProdutoService;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	
	@GetMapping(value= "/all", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listAll(){
		return new ResponseEntity<>(produtoRepository.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> list(Pageable pageable){
		return new ResponseEntity<>(produtoRepository.findAll(pageable), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getById(@PathVariable Integer id) {
		return produtoRepository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping(produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> save(@RequestBody Produto obj) {
		Produto produto = obj;
		produtoRepository.save(produto);
		return obj.getId() != null ? ResponseEntity.status(HttpStatus.CREATED).body(produto)
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro");
	}

	@PutMapping(value = "/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ProdutoDTO newProduto) throws OperationsException {
		return ResponseEntity.ok().body(produtoService.atualizar(id, newProduto));
	}

	@DeleteMapping(value = "/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		produtoRepository.deleteById(id);
		return ResponseEntity.ok().body("Excluido com sucesso.");
	}
	
	@GetMapping(value = "/pesquisa", produces= MediaType.APPLICATION_JSON_VALUE)
	public Page<Produto> pesquisar(@RequestParam(required = false) String nome, @RequestParam(required = false) String categoria, Pageable pageable) {
		return produtoService.filtrar(nome, categoria, pageable);
	}
	
}
