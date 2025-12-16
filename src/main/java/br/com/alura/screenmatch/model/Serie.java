package br.com.alura.screenmatch.model;

import br.com.alura.screenmatch.service.traducao.ConsultaMyMemory;
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
    this.sinopse = ConsultaMyMemory.obterTraducao(dados.sinopse()).trim();
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public Integer getTotalTemporadas() {
    return totalTemporadas;
  }

  public void setTotalTemporadas(Integer totalTemporadas) {
    this.totalTemporadas = totalTemporadas;
  }

  public Double getAvaliacao() {
    return avaliacao;
  }

  public void setAvaliacao(Double avaliacao) {
    this.avaliacao = avaliacao;
  }

  public Categoria getGenero() {
    return genero;
  }

  public void setGenero(Categoria genero) {
    this.genero = genero;
  }

  public String getAtoresStr() {
    return atoresStr;
  }

  public void setAtoresStr(String atoresStr) {
    this.atoresStr = atoresStr;
  }

  public String getPoster() {
    return poster;
  }

  public void setPoster(String poster) {
    this.poster = poster;
  }

  public String getSinopse() {
    return sinopse;
  }

  public void setSinopse(String sinopse) {
    this.sinopse = sinopse;
  }

  @Override
  public String toString() {
    return "Serie{" +
        "titulo='" + titulo + '\'' +
        ", totalTemporadas=" + totalTemporadas +
        ", avaliacao=" + avaliacao +
        ", genero=" + genero +
        ", atoresStr='" + atoresStr + '\'' +
        ", poster='" + poster + '\'' +
        ", sinopse='" + sinopse + '\'' +
        '}';
  }
}
