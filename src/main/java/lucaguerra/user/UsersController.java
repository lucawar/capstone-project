package lucaguerra.user;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import lucaguerra.gastronomia.Gastronomia;
import lucaguerra.prenotazione.NewUserPrenotazioniPayload;
import lucaguerra.prenotazione.Prenotazione;
import lucaguerra.prenotazione.PrenotazioneService;
import lucaguerra.recensione.Recensione;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	UsersService userService;

	@Autowired
	PrenotazioneService ps;

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public User saveUser(@RequestBody @Validated NewUserPayload body) {
		User createdUser = userService.save(body);
		return createdUser;
	}

	@GetMapping("")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Page<User> getUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "id") String sortBy) {
		return userService.find(page, size, sortBy);
	}

	@GetMapping("/{userId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public User findById(@PathVariable UUID userId) {
		return userService.findById(userId);
	}

	@PutMapping("/{userId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public User updateUser(@PathVariable UUID userId, @RequestBody NewUserPayload body) {
		return userService.findByIdAndUpdate(userId, body);
	}

	@DeleteMapping("/{userId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable UUID userId) {
		userService.findByIdAndDelete(userId);
	}

	// ---------------METODI PER I PREFERITI DELLO USER------------

//	Usiamo ResponseEntity<?> perchè ho bisogno di una risposta dinamica, è una classe che rappresenta l'intera risposta HTTP

	// AGGIUNGI GASTRONOMIA AI PREFERITI USER
	@PostMapping("/aggiungiPreferiti/{gastronomiaId}")
	public ResponseEntity<?> addFavorite(@PathVariable UUID gastronomiaId) {
		userService.addFavoriteGastronomia(gastronomiaId);
		return ResponseEntity.ok("Gastronomia aggiunta ai preferiti");
	}

	// RIMUOVI GASTRONOMIA DAI PREFERITI USER
	@DeleteMapping("/rimuoviPreferiti/{gastronomiaId}")
	public ResponseEntity<?> removeFavorite(@PathVariable UUID gastronomiaId) {
		userService.removeFavoriteGastronomia(gastronomiaId);
		return ResponseEntity.ok("Gastronomia rimossa dai preferiti");
	}

	// TORNA LA LISTA DELLE GASTRONOMIE PREFERITE
	@GetMapping("/preferiti")
	public ResponseEntity<Page<Gastronomia>> getUserPreferiti(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {

		Page<Gastronomia> favorites = userService.getUsergGastronomiePreferite(page, size);
		return ResponseEntity.ok(favorites);
	}

	// METODI RECENSIONI

	// TORNA LA LISTA DELLE RECENSIONI DEL CLIENTE LOGGATO
	@GetMapping("/myRecensioni")
	public Page<Recensione> getMyRecensioni(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return userService.getUserRecensioni(page, size);
	}

	// METODI PRENOTAZIONI
	@PostMapping("/prenotazioni")
	@ResponseStatus(HttpStatus.CREATED)
	public Prenotazione creaPrenotazionePerUtenteLoggato(@RequestBody @Validated NewUserPrenotazioniPayload body) {
		return userService.creaPrenotazionePerUtenteLoggato(body);
	}

	// TORNA LA LISTA DELLE PRENOTAZIONI DEL CLIENTE LOGGATO
	@GetMapping("/myPrenotazioni")
	public Page<Prenotazione> getMiePrenotazioni(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return userService.getPrenotazioniPerUtente(page, size, sortBy);
	}

	// FILTRA LA LISTA DELLE PRENOTAZIONI DEL CLIENTE LOGGATO
	@GetMapping("/myPrenotazioni/data")
	public Page<Prenotazione> getMiePrenotazioniByDate(@RequestParam LocalDate dataPrenotazione,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		return userService.findUserPrenotazioniByDate(dataPrenotazione, page, size);
	}
}
