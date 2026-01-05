package br.com.alura.screenmatch.exercicios.modelos;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProcessadorDeEntradas {

  public void exibirEntradasValidas(List<String> entradasExemplo) {
    List<Integer> numerosValidos = filtrarApenasInteiros(entradasExemplo);
    System.out.println(numerosValidos);
  }

  private List<Integer> filtrarApenasInteiros(List<String> entradas) {
    return entradas.stream()
        .map(this::converteParaInteiroOuNullo)
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
  }

  private Integer converteParaInteiroOuNullo(String valor) {
    try {
      return Integer.parseInt(valor);
    } catch (NumberFormatException error) {
      return null;
    }
  }
}
