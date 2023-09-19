package lucaguerra.prenotazione;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lucaguerra.exceptions.NotFoundException;

@Service
public class PrenotazioneService {

	@Autowired
	PrenotazioneRepository pr;

	// SALVA NUOVA PRENOTAZIONE
	public Prenotazione save(NewPrenotazionePayload body) {

		Prenotazione newPrenotazione = new Prenotazione(body.getDataPrenotazione(), body.getOraPrenotazione(),
				body.getNota(), body.getNumeroPersone(), body.getUser(), body.getGastronomia());
		return pr.save(newPrenotazione);
	}

	// GET LISTA PRENOTAZIONE
	public List<Prenotazione> getPrenotazioni() {
		return pr.findAll();
	}

	// GET LISTA PRENOTAZIONE CON IMPAGINAZIONE
	public Page<Prenotazione> find(int page, int size, String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

		return pr.findAll(pageable);
	}

	// CERCA PRENOTAZIONE TRAMITE ID
	public Prenotazione findById(UUID id) throws NotFoundException {
		return pr.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	// CERCA E MODIFICA PRENOTAZIONE TRAMITE ID
	public Prenotazione findByIdAndUpdate(UUID id, NewPrenotazionePayload body) throws NotFoundException {
		Prenotazione found = this.findById(id);
		found.setDataPrenotazione(body.getDataPrenotazione());
		found.setNota(body.getNota());
		found.setNumeroPersone(body.getNumeroPersone());
		return pr.save(found);
	}

	// CANCELLA PRENOTAZIONE TRAMITE ID
	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Prenotazione found = this.findById(id);
		pr.delete(found);

	}

	// METODI SPOSTATI SU USER SERVICE E USER CONTROLLER........CHIEDERE SE è GIUSTO

	// !!!!!!!!!!!!!!!!IMPORTANTE!!!!!!!!!!!!!!!!
	// GESTIRE LE ECCEZIONI, RICONTROLLARE NotFoundException

//	// TROVA SOLO PRENOTAZIONI DELL'UTENTE LOGGATO
//	public Page<Prenotazione> trovaPrenotazioniPerUtente(UUID userId, int page, int size, String sortBy)
//			throws NotFoundException {
//		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
//		Page<Prenotazione> prenotazioni = pr.findByUserId(userId, pageable);
//
//		if (prenotazioni.isEmpty()) {
//			throw new NotFoundException("Nessuna prenotazione trovata per l'utente con ID: " + userId);
//		}
//
//		return prenotazioni;
//	}
//
//	// FILTRA PRENOTAZIONI PER DATA SOLO PER L'UTENTE LOGGATO
//	public Page<Prenotazione> findUserPrenotazioniByDate(UUID userId, LocalDate dataPrenotazione, int page, int size)
//			throws NotFoundException {
//		Pageable pageable = PageRequest.of(page, size, Sort.by("dataPrenotazione"));
//		return pr.findByUserIdAndDataPrenotazione(userId, dataPrenotazione, pageable);
//	}

}
