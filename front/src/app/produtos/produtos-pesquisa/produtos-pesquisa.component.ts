import { Component, OnInit, ViewChild } from '@angular/core';
import { LazyLoadEvent, ConfirmationService } from 'primeng/components/common/api';
import { ToastyService } from 'ng2-toasty';
import { ErrorHandlerService } from './../../core/error-handler.service';

import { CategoriaService } from './../../categorias/categoria.service';
import { ProdutoService, ProdutoFiltro } from './../produto.service';


@Component({
  selector: 'app-produtos-pesquisa',
  templateUrl: './produtos-pesquisa.component.html',
  styleUrls: ['./produtos-pesquisa.component.css']
})
export class ProdutosPesquisaComponent implements OnInit {

  totalRegistros = 0;
  filtro = new ProdutoFiltro();
  produtos = [];
  categorias = [
    { label: 'Selecione', value: null },
    { label: 'Perecível', value: 'PERECIVEL' },
    { label: 'Não Perecível', value: 'NAO_PERECIVEL' },
  ];

  @ViewChild('tabela') grid;

  constructor(
    private produtoService: ProdutoService,
    private categoriaService: CategoriaService,
    private errorHandler: ErrorHandlerService,
    private toasty: ToastyService,
    private confirmation: ConfirmationService
  ) { }

  ngOnInit() {

  }

  pesquisar(pagina = 0) {
    this.filtro.pagina = pagina;
    this.produtoService.pesquisar(this.filtro)
      .then(resultado => {
        this.totalRegistros = resultado.total;
        this.produtos = resultado.produtos;
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  aoMudarPagina(event: LazyLoadEvent) {
    const pagina = event.first / event.rows;
    this.pesquisar(pagina);
  }

  confirmarExclusao(produto: any) {
    this.confirmation.confirm({
      message: 'Deseja realmente excluir o produto ' + produto.nome + ' ?',
      accept: () => {
        this.excluir(produto);
      }
    });
  }

  excluir(produto: any) {
    this.produtoService.excluir(produto.id)
      .then(() => {
        if (this.grid.first === 0) {
          this.pesquisar();
        } else {
          this.grid.first = 0;
        }
        this.toasty.success('Produto excluído com sucesso!');
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

}
