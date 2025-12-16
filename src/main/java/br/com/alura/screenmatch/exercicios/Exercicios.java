package br.com.alura.screenmatch.exercicios;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Exercicios {
  List<String> input = Arrays.asList("10", "abc", "20", "30x");

  public void processaDados() {
    List<Integer> resultado = input.stream()
        .map(s -> {
          try {
            return Integer.parseInt(s);
          } catch (NumberFormatException e) {
            return null;
          }
        })
        .filter(i -> i != null)
        .collect(Collectors.toList());
    System.out.println(resultado);
  }

  public static Optional<Integer> processaNumero(Optional<Integer> numero) {
    numero.ifPresentOrElse(
        n -> System.out.println("Número presente: " + n*n),
        () -> System.out.println("Número ausente")
    );
    return numero.map(n -> n * n);
  }

  public static String obterPrimeiroEUltimoNome(String nomeCompleto) {
    return nomeCompleto.trim();
  }

  public static boolean ehPalindromo(String palavra) {
    String limpo = palavra.replace(" ", "").toLowerCase();
    String palindromo = new StringBuilder(limpo).reverse().toString();
    if (limpo.equals(palindromo)) {
      return true;
    } else {
      return false;
    }
  }
}
