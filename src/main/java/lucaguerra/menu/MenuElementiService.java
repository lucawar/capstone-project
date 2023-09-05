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
public class MenuElementiService {

	@Autowired
	MenuElementiRepository mer;

	// SALVA NUOVA MENU_ELEMENTI
	public MenuElementi save(NewMenuElementiPayload body) {

		MenuElementi newMenuElementi = new MenuElementi(body.getNome(), body.getPrezzo(), body.getDescrizione(),
				body.getCategoria(), body.getMenu());
		return mer.save(newMenuElementi);
	}

	// TORNA LA LISTA DEI MENU_ELEMENTI
	public List<MenuElementi> getMenuElementi() {
		return mer.findAll();
	}

	// TORNA LA LISTA DEI MENU_ELEMENTI CON L'IMPAGINAZIONE
	public Page<MenuElementi> find(int page, int size, String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

		return mer.findAll(pageable);
	}

	// CERCA MENU_ELEMENTI TRAMITE ID
	public MenuElementi findById(UUID id) throws NotFoundException {
		return mer.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	// CERCA E MODIFICA MENU_ELEMENTI TRAMITE ID
	public MenuElementi findByIdAndUpdate(UUID id, NewMenuElementiPayload body) throws NotFoundException {
		MenuElementi found = this.findById(id);
		found.setNome(body.getNome());
		found.setPrezzo(body.getPrezzo());
		found.setDescrizione(body.getDescrizione());
		found.setCategoria(body.getCategoria());
		found.setMenu(body.getMenu());
		return mer.save(found);
	}

	// CANCELLA MENU_ELEMENTI TRAMITE ID
	public void findByIdAndDelete(UUID id) throws NotFoundException {
		MenuElementi found = this.findById(id);
		mer.delete(found);
	}
}
