package br.com.alura.screenmatch.exercicios;

import br.com.alura.screenmatch.exercicios.modelos.CalculadoraOpcional;
import br.com.alura.screenmatch.exercicios.modelos.VerificadorDePalindromo;
import br.com.alura.screenmatch.exercicios.modelos.ProcessadorDeEntradas;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Exercicios {
  List<String> entradasExemplo = Arrays.asList("10", "abc", "20", "30x");

//  public void extrairEntradasValidas() {
//    List<Integer> entradasValidas = entradasExemplo.stream()
//        .map(elemento -> {
//          try {
//            return Integer.parseInt(elemento);
//          } catch (NumberFormatException error) {
//            return null;
//          }
//        })
//        .filter(numero -> numero != null)
//        .collect(Collectors.toList());
//    System.out.println(entradasValidas);
//  }

  // Já fazendo o uso do Clean Code

  public void executar() {
    ProcessadorDeEntradas processador = new ProcessadorDeEntradas();
    processador.exibirEntradasValidas(entradasExemplo);

    CalculadoraOpcional calculadoraOpcional = new CalculadoraOpcional();
    calculadoraOpcional.executarLogicaDeNegocio(Optional.of(5));
    calculadoraOpcional.executarLogicaDeNegocio(Optional.of(-3));
    calculadoraOpcional.executarLogicaDeNegocio(Optional.empty());

    VerificadorDePalindromo palindromo = new VerificadorDePalindromo();
    palindromo.verificar("socorram me subi no onibus em marrocos");
    palindromo.verificar("Java");
    palindromo.verificar("ana ana");
  }

  public static String obterPrimeiroEUltimoNome(String nomeCompleto) {
    return nomeCompleto.trim();
  }

  public static List<String> converterEmails(List<String> emails) {
    return emails.stream().map(String::toLowerCase).collect(Collectors.toList());
  }
}
