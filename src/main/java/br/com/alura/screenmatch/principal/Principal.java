package br.com.alura.screenmatch.principal;

import static br.com.alura.screenmatch.exercicios.Exercicios.obterPrimeiroEUltimoNome;
import static br.com.alura.screenmatch.exercicios.Exercicios.converterEmails;


import br.com.alura.screenmatch.exercicios.Exercicios;
import br.com.alura.screenmatch.model.SerieEntity;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.Mes;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepository;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

  private final String ENDERECO = "https://www.omdbapi.com/?t=";
  private final String API_KEY = "&apikey=6585022c";
  private Scanner leitura = new Scanner(System.in);
  private ConsumoApi consumo = new ConsumoApi();
  private ConverteDados conversor = new ConverteDados();
  private List<SerieEntity> serySchemes = new ArrayList<>();
  private SerieRepository repositorio;
  private List<Serie> series = new ArrayList<>();

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
                buscarESalvarNovaSerie();
                break;
            case 2:
                buscarEpisodioPorSerie();
                break;
            case 3:
                  exibirSeriesOrdenadasPesquisadas();
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

    private String pedirNomeDaSerie() {
      System.out.println("Digite o nome da série para busca:");
      return leitura.nextLine();
    }

    private void buscarESalvarNovaSerie() {
        SerieEntity serieEntity = pesquisarSerie();
        Serie serie = new Serie(serieEntity);
        //dadosSeries.add(dados);
        repositorio.save(serie);
        System.out.println(serieEntity);
    }

    private void exibirSeriesOrdenadasPesquisadas() {
//      List<Serie> series = dadosSeries.stream()
//              .map(Serie::new)
//              .collect(Collectors.toList());
//      List<Serie> series = repositorio.findAll();
//
//      series.stream()
//              .sorted(Comparator.comparing(Serie::getTitulo))
//                  .forEach(System.out::println);

      series = repositorio.findAllByOrderByTituloAsc();
      series.forEach(System.out::println);
    }

    private SerieEntity pesquisarSerie() {
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        SerieEntity serie = conversor.obterDados(json, SerieEntity.class);
        return serie;
    }

    private void buscarEpisodioPorSerie(){
        //SerieEntity serieEntity = pesquisarSerie();
        exibirSeriesOrdenadasPesquisadas();
        var nomeDaSerie = pedirNomeDaSerie();

      Optional<Serie> first = series.stream()
          .filter(serie -> serie.getTitulo().toLowerCase().contains(nomeDaSerie.toLowerCase()))
          .findFirst();

      if(first.isPresent()){
        var serieEncontrada = first.get();
        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= serieEncontrada.getTotalTemporadas(); i++) {
          var json = consumo.obterDados(ENDERECO + serieEncontrada.getTitulo().replace(" ", "+") + "&season=" + i + API_KEY);
          DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
          temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);
      } else {
          System.out.println("Série não encontrada na lista de pesquisadas.");
      }



    }

    private void exercicios() {
      Exercicios teste = new Exercicios();
      teste.executar();

      System.out.println(obterPrimeiroEUltimoNome("  João Carlos Silva   ")); // Saída: "João Silva"
      System.out.println(obterPrimeiroEUltimoNome("Maria   "));

      List<String> emails = Arrays.asList("TESTE@EXEMPLO.COM", "exemplo@Java.com ", "Usuario@teste.Com");
      System.out.println(converterEmails(emails));

      System.out.println(Mes.FEVEREIRO.getNumeroDeDias()); // 28
      System.out.println(Mes.JULHO.getNumeroDeDias()); // 31
    }
}