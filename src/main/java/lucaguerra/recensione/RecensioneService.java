package lucaguerra.recensione;

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
public class RecensioneService {

	@Autowired
	RecensioneRepository rr;

	// SALVA NUOVA RECENSIONE
	public Recensione save(NewRecensionePayload body) {

		Recensione newRecensione = new Recensione(body.getValutazione(), body.getCommento(), body.getUser(),
				body.getGastronomia());
		return rr.save(newRecensione);
	}

	// TORNA LA LISTA DELLE RECENSIONI
	public List<Recensione> getRecensione() {
		return rr.findAll();
	}

	// TORNA LA LISTA DEllE RECENSIONI CON L'IMPAGINAZIONE
	public Page<Recensione> find(int page, int size, String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

		return rr.findAll(pageable);
	}

	// CERCA RECENSIONE TRAMITE ID
	public Recensione findById(UUID id) throws NotFoundException {
		return rr.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	// CERCA E MODIFICA RECENSIONE TRAMITE ID
	public Recensione findByIdAndUpdate(UUID id, NewRecensionePayload body) throws NotFoundException {
		Recensione found = this.findById(id);
		found.setValutazione(body.getValutazione());
		found.setCommento(body.getCommento());
		return rr.save(found);
	}

	// CANCELLA RECENSIONE TRAMITE ID
	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Recensione found = this.findById(id);
		rr.delete(found);
	}

}
