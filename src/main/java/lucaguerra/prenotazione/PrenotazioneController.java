package lucaguerra.prenotazione;

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
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

	@Autowired
	PrenotazioneService ps;

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Prenotazione savePrenotazione(@RequestBody @Validated NewPrenotazionePayload body) {
		Prenotazione createdPrenotazione = ps.save(body);
		return createdPrenotazione;
	}

	@GetMapping("")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Page<Prenotazione> getPrenotazione(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return ps.find(page, size, sortBy);
	}

	@GetMapping("/{prenotazioneId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Prenotazione findById(@PathVariable UUID prenotazioneId) {
		return ps.findById(prenotazioneId);
	}

	@PutMapping("/{prenotazioneId}")
	public Prenotazione updatePrenotazione(@PathVariable UUID prenotazioneId,
			@RequestBody NewPrenotazionePayload body) {
		return ps.findByIdAndUpdate(prenotazioneId, body);
	}

	@DeleteMapping("/{prenotazioneId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePrenotazione(@PathVariable UUID prenotazioneId) {
		ps.findByIdAndDelete(prenotazioneId);
	}

//	// TORNA LE PRENOTAZIONI DI UN UTENTE LOGGATO
//	@GetMapping("/my")
//	public Page<Prenotazione> getPrenotazioni(@RequestParam(defaultValue = "0") int page,
//			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
//		User utenteAutenticato = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		return ps.trovaPrenotazioniPerUtente(utenteAutenticato.getId(), page, size, sortBy);
//	}
//
//	// FILTRA LE PRENOTAZIONI DI UN UTENTE LOGGATO PER DATA
//	@GetMapping("/my/data")
//	public Page<Prenotazione> getMyPrenotazioniByDate(@RequestParam LocalDate dataPrenotazione,
//			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
//		User utenteAutenticato = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		return ps.findUserPrenotazioniByDate(utenteAutenticato.getId(), dataPrenotazione, page, size);
//	}

}
