package lucaguerra.recensione;

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

@RestController
@RequestMapping("/recensioni")
public class RecensioneController {

	@Autowired
	RecensioneService rs;

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Recensione saveRecensione(@RequestBody @Validated NewRecensionePayload body) {
		Recensione createdRecensione = rs.save(body);
		return createdRecensione;
	}

	@GetMapping("")
	public Page<Recensione> getRecensione(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return rs.find(page, size, sortBy);
	}

	@GetMapping("/{recensioneId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Recensione findById(@PathVariable UUID recensioneId) {
		return rs.findById(recensioneId);
	}

	@PutMapping("/{recensioneId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Recensione updateRecensione(@PathVariable UUID recensioneId, @RequestBody NewRecensionePayload body) {
		return rs.findByIdAndUpdate(recensioneId, body);
	}

	@DeleteMapping("/{recensioneId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteRecensione(@PathVariable UUID recensioneId) {
		rs.findByIdAndDelete(recensioneId);
	}

}
