package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie(@JsonAlias("Title") String titulo,
                         @JsonAlias("totalSeasons") Integer totalTemporadas,
                         @JsonAlias("imdbRating")  String avaliacao,
                         @JsonAlias("Genre") String genero,
                         @JsonAlias("Actors") String atoresStr,
                         @JsonAlias("Poster") String poster,
                         @JsonAlias("Plot") String sinopse) {

}
