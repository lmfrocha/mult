package br.com.gm.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.gm.api.model.Produto;
import br.com.gm.api.model.enums.Categoria;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
	
	@Query("SELECT p FROM Produto p WHERE p.nome LIKE :nome AND p.categoria =:categoria")
    public Page<Produto> findByNomeOrCategoria(String nome, Categoria categoria, Pageable pageable);
	
	@Query("SELECT p FROM Produto p WHERE p.nome LIKE :nome")
    public Page<Produto> findByNome(String nome, Pageable pageable);
	
	@Query("SELECT p FROM Produto p WHERE p.categoria =:categoria")
    public Page<Produto> findByCategoria(Categoria categoria, Pageable pageable);
	
}
