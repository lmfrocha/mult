import { Http, Headers, URLSearchParams } from '@angular/http';
import { Injectable } from '@angular/core';

import 'rxjs/add/operator/toPromise';

import { Produto } from './../core/model';

export class ProdutoFiltro {
  nome: string;
  categoria: string;
  pagina = 0;
  itensPorPagina = 5;
}

@Injectable()
export class ProdutoService {

  produtosUrl = 'http://localhost:8080/produtos';

  constructor(private http: Http) { }

  pesquisar(filtro: ProdutoFiltro): Promise<any> {
    const params = new URLSearchParams();
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    params.set('page', filtro.pagina.toString());
    params.set('size', filtro.itensPorPagina.toString());

    if (filtro.nome) {
      params.set('nome', filtro.nome);
    }

    if (filtro.categoria) {
      params.set('categoria', filtro.categoria);
    }
    return this.http.get(`${this.produtosUrl}/pesquisa?`,
        { headers, search: params })
      .toPromise()
      .then(response => {
        const responseJson = response.json();
        const produtos = responseJson.content;

        const resultado = {
          produtos,
          total: responseJson.totalElements
        };

        return resultado;
      });
  }

  excluir(id: number): Promise<void> {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.delete(`${this.produtosUrl}/${id}`, { headers })
      .toPromise()
      .then(() => null);
  }

  adicionar(produto: Produto): Promise<Produto> {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.post(this.produtosUrl,
        JSON.stringify(produto), { headers })
      .toPromise()
      .then(response => response.json());
  }

  atualizar(produto: Produto): Promise<Produto> {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.put(`${this.produtosUrl}/${produto.id}`,
        JSON.stringify(produto), { headers })
      .toPromise()
      .then(response => {
        const produtoAlterado = response.json() as Produto;
        return produtoAlterado;
      });
  }

  buscarPorId(id: number): Promise<Produto> {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get(`${this.produtosUrl}/${id}`, { headers })
      .toPromise()
      .then(response => {
        const produto = response.json() as Produto;
        return produto;
      });
  }

}
