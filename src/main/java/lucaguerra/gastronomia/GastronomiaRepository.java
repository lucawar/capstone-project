package lucaguerra.gastronomia;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import lucaguerra.enums.TipoGastronomia;

@Repository
public interface GastronomiaRepository extends JpaRepository<Gastronomia, UUID> {

	@Query("SELECT g FROM Gastronomia g WHERE " + "(?1 IS NULL OR LOWER(g.nome) LIKE LOWER(CONCAT('%', ?1, '%'))) AND "
			+ "(?2 IS NULL OR g.tipoGastronomia = ?2) AND " + "(?3 IS NULL OR g.indirizzo = ?3) AND "
			+ "(?4 IS NULL OR g.prezzoMedio >= ?4) AND " + "(?5 IS NULL OR g.prezzoMedio <= ?5) "
			+ "ORDER BY g.prezzoMedio")
	Page<Gastronomia> searchGastronomia(String nome, TipoGastronomia tipo, String indirizzo, Integer prezzoMin,
			Integer prezzoMax, Pageable pageable);
}