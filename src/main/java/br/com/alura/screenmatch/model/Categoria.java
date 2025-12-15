package br.com.alura.screenmatch.model;

public enum Categoria {
  ACAO("Action"),
  AVENTURA("Adventure"),
  ANIMACAO("Animation"),
  COMICS("Comics"),
  DOCUMENTARIO("Documentary"),
  FANTASIA("Fantasy"),
  HISTORICO("History"),
  HORROR("Horror"),
  MUSICAL("Musical"),
  ROMANCE("Romance"),
  COMEDIA("Comedy"),
  DRAMA("Drama"),
  CRIME("Crime");

  private String categoriaOmdb;

  Categoria(String categoriaOmdb) {
    this.categoriaOmdb = categoriaOmdb;
  }

  public static Categoria fromString(String text) {
    for (Categoria categoria : Categoria.values()) {
      if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
        return categoria;
      }
    }
    throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
  }
}
