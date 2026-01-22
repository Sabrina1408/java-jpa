package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.model.Categoria;
import br.com.alura.screenmatch.model.Serie;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepository extends JpaRepository<Serie, Long> {
  List<Serie> findAllByOrderByTituloAsc();
  Optional<Serie> findByTituloContainingIgnoreCase(String titulo);

  List<Serie> findByAtoresStrContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(String nomeAtor, Double avaliacao);

  List<Serie> findByGeneroContainningIgnoreCase(Categoria genero);
}
