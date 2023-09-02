package lucaguerra.gastronomia;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GastronomiaRepository extends JpaRepository<Gastronomia, UUID> {

}
