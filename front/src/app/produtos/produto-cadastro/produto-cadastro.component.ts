import { FormControl } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ToastyService } from 'ng2-toasty';

import { ErrorHandlerService } from './../../core/error-handler.service';
import { CategoriaService } from './../../categorias/categoria.service';
import { Produto } from './../../core/model';
import { ProdutoService } from './../produto.service';

@Component({
  selector: 'app-produto-cadastro',
  templateUrl: './produto-cadastro.component.html',
  styleUrls: ['./produto-cadastro.component.css']
})
export class ProdutoCadastroComponent implements OnInit {

  categorias = [
    { label: 'Selecione', value: null },
    { label: 'Perecível', value: 'PERECIVEL' },
    { label: 'Não Perecível', value: 'NAO_PERECIVEL' },
  ];

  pessoas = [];
  produto = new Produto();

  constructor(
    private categoriaService: CategoriaService,
    private produtoService: ProdutoService,
    private toasty: ToastyService,
    private errorHandler: ErrorHandlerService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    const idProduto = this.route.snapshot.params['id'];

    if (idProduto) {
      this.carregarProduto(idProduto);
    }

  }

  get editando() {
    return Boolean(this.produto.id)
  }

  carregarProduto(id: number) {
    this.produtoService.buscarPorId(id)
      .then(produto => {
        this.produto = produto;
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  salvar(form: FormControl) {
    if (this.editando) {
      this.atualizarProduto(form);
    } else {
      this.adicionarProduto(form);
    }
  }

  adicionarProduto(form: FormControl) {
    this.produtoService.adicionar(this.produto)
      .then(() => {
        this.toasty.success('Produto adicionado com sucesso!');
        form.reset();
        this.produto = new Produto();
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  novo(form: FormControl) {
    form.reset();
  }

  atualizarProduto(form: FormControl) {
    this.produtoService.atualizar(this.produto)
      .then(produto => {
        this.produto = produto;
        this.toasty.success('Produto alterado com sucesso!');
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  carregarCategorias() {
    return this.categoriaService.listarTodas()
      .then(categorias => {
        this.categorias = categorias
          .map(c => ({ label: c.nome, value: c.codigo }));
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

}
