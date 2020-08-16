import { HttpModule } from '@angular/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { CoreModule } from './core/core.module';
import { ProdutosModule } from './produtos/produtos.module';
import { ProdutoCadastroComponent } from './produtos/produto-cadastro/produto-cadastro.component';
import { ProdutosPesquisaComponent } from './produtos/produtos-pesquisa/produtos-pesquisa.component';

const routes: Routes = [
  { path: '', component: ProdutosPesquisaComponent },
  { path: 'produtos', component: ProdutosPesquisaComponent },
  { path: 'produtos/novo', component: ProdutoCadastroComponent },
  { path: 'produtos/:id', component: ProdutoCadastroComponent }
];

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpModule,
    RouterModule.forRoot(routes),

    CoreModule,
    ProdutosModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
