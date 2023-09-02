package lucaguerra.gastronomia;

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
public class GastronomiaService {

	@Autowired
	GastronomiaRepository gr;

	// SALVA NUOVA GASTRONOMIA
	public Gastronomia save(NewGastronomiaPayload body) {

		Gastronomia newGastronomia = new Gastronomia(body.getNome(), body.getIndirizzo(), body.getTelefono(),
				body.getPrezzoMedio(), body.getTipoGastronomia(), body.getImageUrl());
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
		found.setTipoGastronomia(body.getTipoGastronomia());
		found.setImageUrl(body.getImageUrl());
		return gr.save(found);
	}

	// CANCELLA GASTRONOMIA TRAMITE ID
	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Gastronomia found = this.findById(id);
		gr.delete(found);
	}
}