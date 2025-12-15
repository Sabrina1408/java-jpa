package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.util.OptionalDouble;

public class Serie {
  private String titulo;
  private Integer totalTemporadas;
  private Double avaliacao;
  private Categoria genero;
  private String atoresStr;
  private String poster;
  private String sinopse;

  public Serie(DadosSerie dados) {
    this.titulo = dados.titulo();
    this.totalTemporadas = dados.totalTemporadas();
    this.avaliacao = OptionalDouble.of(Double.valueOf(dados.avaliacao())).orElse(0.0);
    this.genero = Categoria.fromString(dados.genero().split(",")[0].trim());
    this.atoresStr = dados.atoresStr();
    this.poster = dados.poster();
    this.sinopse = dados.sinopse();
  }
}
