package br.com.gm.api.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gm.api.model.enums.Categoria;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@GetMapping
	public ResponseEntity<?> listar(){
		return ResponseEntity.ok().body(Categoria.getCategorias());
	}
	
}