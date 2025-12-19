package br.com.alura.screenmatch.model;

public enum Mes {
  JANEIRO("Janeiro", 31),
  FEVEREIRO("Fevereiro", 28),
  MARCO("Março", 31),
  ABRIL("Abril", 30),
  MAIO("Maio", 31),
  JUNHO("Junho", 30),
  JULHO("Julho", 31),
  AGOSTO("Agosto", 31),
  SETEMBRO("Setembro", 30),
  OUTUBRO("Outubro", 31),
  NOVEMBRO("Novembro", 30),
  DEZEMBRO("Dezembro", 31);

  private String nomeMes;
  private int numeroDeDias;

  Mes(String nomeMes, int numeroDeDias) {
    this.nomeMes = nomeMes;
    this.numeroDeDias = numeroDeDias;
  }

  public int getNumeroDeDias() {
    return numeroDeDias;
  }

  public String getNomeMes() {
    return nomeMes;
  }
}
