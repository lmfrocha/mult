package br.com.gm.api.services;

import java.util.Optional;

import javax.management.OperationsException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.gm.api.model.Produto;
import br.com.gm.api.model.dto.ProdutoDTO;
import br.com.gm.api.model.enums.Categoria;
import br.com.gm.api.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;

	/**
	 * 
	 * @param produto
	 * @return
	 */
	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}

	/**
	 * 
	 * @param id
	 * @param newProduto
	 * @return
	 * @throws OperationsException 
	 */
	public Boolean atualizar(Integer id, ProdutoDTO newProduto) throws OperationsException {
		Boolean sucesso = false;
		try {
			Produto produtoOld = buscarProdutoById(id);
			BeanUtils.copyProperties(newProduto, produtoOld, "categoria", "valor");
			produtoRepository.save(produtoOld);
			sucesso = true;
		} catch (Exception e) {
			throw new OperationsException("Erro ao atualizar");
		}
		return sucesso;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Produto buscarProdutoById(Integer id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		if (!produto.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return produto.get();
	}
	
	/**
	 * 
	 * @param nome
	 * @param categoria
	 * @param pageable
	 * @return
	 */
	public Page<Produto> filtrar(String nome, String categoria, Pageable pageable) {
		if (StringUtils.hasText(nome) && !StringUtils.hasText(categoria)) {
			return produtoRepository.findByNome("%"+nome+"%", pageable);
		}else if (StringUtils.hasText(categoria) && categoria.equalsIgnoreCase(Categoria.PERECIVEL.toString())) {
			return StringUtils.hasText(nome) ? produtoRepository.findByNomeOrCategoria("%"+nome+"%", Categoria.PERECIVEL, pageable) :
				produtoRepository.findByCategoria(Categoria.PERECIVEL, pageable);
		}else if (StringUtils.hasText(categoria) && categoria.equalsIgnoreCase(Categoria.NAO_PERECIVEL.toString())) {
			return StringUtils.hasText(nome) ? produtoRepository.findByNomeOrCategoria("%"+nome+"%", Categoria.NAO_PERECIVEL, pageable) :
				produtoRepository.findByCategoria(Categoria.NAO_PERECIVEL, pageable);
		} else {
			return produtoRepository.findAll(pageable);
		}
	}
}
