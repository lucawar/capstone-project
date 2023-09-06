package lucaguerra.menu;

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
public class MenuService {

	@Autowired
	MenuRepository mr;

	// SALVA NUOVO MENU
	public Menu save(NewMenuPayload body) {

		Menu newMenu = new Menu(body.getNome());
		return mr.save(newMenu);
	}

	// GET LISTA MENU
	public List<Menu> getMenu() {
		return mr.findAll();
	}

	// GET LISTA MENU CON IMPAGINAZIONE
	public Page<Menu> find(int page, int size, String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

		return mr.findAll(pageable);
	}

	// CERCA MENU TRAMITE ID
	public Menu findById(UUID id) throws NotFoundException {
		return mr.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	// CERCA E MODIFICA PRENOTAZIONE TRAMITE ID
	public Menu findByIdAndUpdate(UUID id, NewMenuPayload body) throws NotFoundException {
		Menu found = this.findById(id);
		found.setNome(body.getNome());
		return mr.save(found);
	}

	// CANCELLA PRENOTAZIONE TRAMITE ID
	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Menu found = this.findById(id);
		mr.delete(found);

	}
}
