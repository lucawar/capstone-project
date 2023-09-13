package lucaguerra.gastronomia;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lucaguerra.enums.TipoGastronomia;
import lucaguerra.exceptions.NotFoundException;
import lucaguerra.menu.Menu;
import lucaguerra.recensione.Recensione;

@Service
public class GastronomiaService {

	@Autowired
	GastronomiaRepository gr;

	// SALVA NUOVA GASTRONOMIA
	public Gastronomia save(NewGastronomiaPayload body) {

		Gastronomia newGastronomia = new Gastronomia(body.getNome(), body.getIndirizzo(), body.getTelefono(),
				body.getPrezzoMedio(), body.getDescrizione(), body.getTipoGastronomia(), body.getImageUrl());
		return gr.save(newGastronomia);
	}

	// TORNA LA LISTA DEGLI UTENTI
	public List<Gastronomia> getGastronomia() {
		return gr.findAll();
	}

	// TORNA LA LISTA DElle GASTRONOMIE CON L'IMPAGINAZIONE
	public Page<Gastronomia> find(int page, int size, String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

		return gr.findAll(pageable);
	}

	// CERCA GASTRONOMIA TRAMITE ID
	public Gastronomia findById(UUID id) throws NotFoundException {
		return gr.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	// CERCA E MODIFICA GASTRONOMIA TRAMITE ID
	public Gastronomia findByIdAndUpdate(UUID id, NewGastronomiaPayload body) throws NotFoundException {
		Gastronomia found = this.findById(id);
		found.setNome(body.getNome());
		found.setIndirizzo(body.getIndirizzo());
		found.setTelefono(body.getTelefono());
		found.setPrezzoMedio(body.getPrezzoMedio());
		found.setDescrizione(body.getDescrizione());
		found.setTipoGastronomia(body.getTipoGastronomia());
		found.setImageUrl(body.getImageUrl());
		return gr.save(found);
	}

	// CANCELLA GASTRONOMIA TRAMITE ID
	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Gastronomia found = this.findById(id);
		gr.delete(found);
	}

	// RICERCA PER NOME,TIPO,INDIRIZZO,RANGE DI PREZZO e ORDINA PER PREZZO MEDIO
	public Page<Gastronomia> searchGastronomia(String nome, TipoGastronomia tipo, String indirizzo, Integer prezzoMin,
			Integer prezzoMax, int page, int size, String sortBy) throws NotFoundException {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return gr.searchGastronomia(nome, tipo, indirizzo, prezzoMin, prezzoMax, pageable);
	}

	// TORNA LA LISTA DEL MENU DI UNA GASTRONOMIA
	public Menu findMenuByGastronomia(UUID gastronomiaId) throws NotFoundException {
		Gastronomia gastronomia = this.findById(gastronomiaId);
		return gastronomia.getMenu();
	}

	// TORNA LA LISTA DELLE RECENSIONI DI UNA GASTRONOMIA
	public List<Recensione> getRecensioniByGastronomia(UUID gastronomiaId) throws NotFoundException {
		Gastronomia gastronomia = this.findById(gastronomiaId);
		if (gastronomia.getRecensioni() == null || gastronomia.getRecensioni().isEmpty()) {
			throw new NotFoundException("Non ci sono recensioni per questa gastronomia");
		}
		return gastronomia.getRecensioni();
	}
}
