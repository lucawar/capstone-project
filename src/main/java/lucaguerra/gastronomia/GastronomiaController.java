package lucaguerra.gastronomia;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lucaguerra.enums.TipoGastronomia;
import lucaguerra.exceptions.NotFoundException;

@RestController
@RequestMapping("/gastronomia")
public class GastronomiaController {

	@Autowired
	GastronomiaService gs;

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Gastronomia saveGastronomia(@RequestBody @Validated NewGastronomiaPayload body) {
		Gastronomia createdGastronomia = gs.save(body);
		return createdGastronomia;
	}

	@GetMapping("")
	public Page<Gastronomia> getGastronomia(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return gs.find(page, size, sortBy);
	}

	@GetMapping("/{gastronomiaId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Gastronomia findById(@PathVariable UUID gastronomiaId) {
		return gs.findById(gastronomiaId);
	}

	@PutMapping("/{gastronomiaId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Gastronomia updateGastronomia(@PathVariable UUID gastronomiaId, @RequestBody NewGastronomiaPayload body) {
		return gs.findByIdAndUpdate(gastronomiaId, body);
	}

	@DeleteMapping("/{gastronomiaId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteGastronomia(@PathVariable UUID gastronomiaId) {
		gs.findByIdAndDelete(gastronomiaId);
	}

	// RICERCA PER NOME,TIPO,INDIRIZZO,RANGE DI PREZZO e ORDINA PER PREZZO MEDIO
	@GetMapping("/cerca")
	public Page<Gastronomia> cercaGastronomia(@RequestParam(required = false) String nome,
			@RequestParam(required = false) TipoGastronomia tipo, @RequestParam(required = false) String indirizzo,
			@RequestParam(required = false) Integer prezzoMin, @RequestParam(required = false) Integer prezzoMax,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "id") String sortBy) throws NotFoundException {
		return gs.searchGastronomia(nome, tipo, indirizzo, prezzoMin, prezzoMax, page, size, sortBy);
	}
}