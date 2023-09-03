package lucaguerra.prenotazione;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, UUID> {

	Page<Prenotazione> findByUserId(UUID userId, Pageable pageable);

}
