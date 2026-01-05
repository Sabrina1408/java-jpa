package br.com.alura.screenmatch.exercicios.modelos;

import java.util.Optional;

public class CalculadoraOpcional {


  public void executarLogicaDeNegocio(Optional<Integer> valorEntrada) {
    Optional<Integer> resultado = elevarAoQuadrado(valorEntrada);
    exibirResultado(resultado);
  }

  private Optional<Integer> elevarAoQuadrado(Optional<Integer> numero) {
    return numero.map(n -> n * n);
  }

  private void exibirResultado(Optional<Integer> resultado) {
    resultado.ifPresentOrElse(
        valor -> System.out.println("Resultado calculado: " + valor),
        () -> System.out.println("Não foi possível calcular: valor ausente")
    );
  }
}