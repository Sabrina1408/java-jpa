package br.com.alura.screenmatch.principal;

import static br.com.alura.screenmatch.exercicios.Exercicios.ehPalindromo;
import static br.com.alura.screenmatch.exercicios.Exercicios.obterPrimeiroEUltimoNome;
import static br.com.alura.screenmatch.exercicios.Exercicios.processaNumero;
import static br.com.alura.screenmatch.exercicios.Exercicios.converterEmails;


import br.com.alura.screenmatch.exercicios.Exercicios;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.Mes;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepository;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6585022c";
    private List<DadosSerie> dadosSeries = new ArrayList<>();
    private SerieRepository repositorio;

    public Principal(SerieRepository repositorio) {
        this.repositorio = repositorio;
    }


  public void exibeMenu() {

      var opcao = -1;

      while (opcao != 0)  {
        var menu = """
                1 - Buscar séries
                2 - Buscar episódios
                3 - Listar séries buscadas
                4 - Exercícios
                0 - Sair                                 
                """;

        System.out.println(menu);

        opcao = leitura.nextInt();
        leitura.nextLine();

        switch (opcao) {
            case 1:
                buscarSerieWeb();
                break;
            case 2:
                buscarEpisodioPorSerie();
                break;
            case 3:
                  buscarSeriesPesquisadas();
                  break;
            case 4:
                  exercicios();
                  break;
            case 0:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opção inválida");
        }
      }
    }

    private void buscarSerieWeb() {
        DadosSerie dados = getDadosSerie();
        Serie serie = new Serie(dados);
        //dadosSeries.add(dados);
        repositorio.save(serie);
        System.out.println(dados);
    }

    private void buscarSeriesPesquisadas() {
      List<Serie> series = dadosSeries.stream()
              .map(Serie::new)
              .collect(Collectors.toList());

      series.stream()
              .sorted(Comparator.comparing(Serie::getGenero))
                  .forEach(System.out::println);
    }

    private DadosSerie getDadosSerie() {
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        return dados;
    }

    private void buscarEpisodioPorSerie(){
        DadosSerie dadosSerie = getDadosSerie();
        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
            var json = consumo.obterDados(ENDERECO + dadosSerie.titulo().replace(" ", "+") + "&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);
    }

    private void exercicios() {
      Exercicios teste = new Exercicios();
      teste.processaDados();

      processaNumero(Optional.of(5)); // Saída: Optional[25]
      processaNumero(Optional.of(-3)); // Saída: Optional.empty
      processaNumero(Optional.empty());

      System.out.println(obterPrimeiroEUltimoNome("  João Carlos Silva   ")); // Saída: "João Silva"
      System.out.println(obterPrimeiroEUltimoNome("Maria   "));

      System.out.println(ehPalindromo("socorram me subi no onibus em marrocos")); // Saída: true
      System.out.println(ehPalindromo("Java"));
      System.out.println(ehPalindromo("ana ana"));

      List<String> emails = Arrays.asList("TESTE@EXEMPLO.COM", "exemplo@Java.com ", "Usuario@teste.Com");
      System.out.println(converterEmails(emails));

      System.out.println(Mes.FEVEREIRO.getNumeroDeDias()); // 28
      System.out.println(Mes.JULHO.getNumeroDeDias()); // 31
    }
}