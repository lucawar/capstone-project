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
import lucaguerra.gastronomia.Gastronomia;
import lucaguerra.gastronomia.GastronomiaRepository;

@Service
public class MenuService {

	@Autowired
	MenuRepository mr;

	@Autowired
	GastronomiaRepository gastonomiaRepository;

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

	// CERCA E MODIFICA MENU TRAMITE ID
	public Menu findByIdAndUpdate(UUID id, NewMenuPayload body) throws NotFoundException {
		Menu found = this.findById(id);
		found.setNome(body.getNome());
		return mr.save(found);
	}

	// CANCELLA MENU TRAMITE ID
	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Menu found = this.findById(id);
		mr.delete(found);

	}

	// AGGIUNGI MENU A GASTRONOMIA
	public Gastronomia assegnaMenuToGastronomia(UUID gastronomiaId, UUID menuId) throws NotFoundException {
		Gastronomia gastronomia = gastonomiaRepository.findById(gastronomiaId)
				.orElseThrow(() -> new NotFoundException(gastronomiaId));
		Menu menu = mr.findById(menuId).orElseThrow(() -> new NotFoundException(menuId));
		gastronomia.setMenu(menu);
		return gastonomiaRepository.save(gastronomia);
	}

}
