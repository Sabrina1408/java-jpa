package br.com.alura.screenmatch.exercicios.modelos;

public class VerificadorDePalindromo {

  public boolean verificar(String texto) {
    String textoFormatado = formatar(texto);
    String textoInvertido = inverter(textoFormatado);

    return textoFormatado.equals(textoInvertido);
  }

  private String formatar(String texto) {
    return texto.replace(" ", "").toLowerCase();
  }

  private String inverter(String texto) {
    return new StringBuilder(texto).reverse().toString();
  }

}
