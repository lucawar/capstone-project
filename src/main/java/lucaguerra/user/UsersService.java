package lucaguerra.user;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lucaguerra.exceptions.BadRequestException;
import lucaguerra.exceptions.NotFoundException;
import lucaguerra.gastronomia.Gastronomia;
import lucaguerra.gastronomia.GastronomiaRepository;
import lucaguerra.prenotazione.Prenotazione;
import lucaguerra.prenotazione.PrenotazioneRepository;
import lucaguerra.recensione.Recensione;
import lucaguerra.recensione.RecensioneRepository;

@Service
public class UsersService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PrenotazioneRepository prenotazioneRepository;

	@Autowired
	GastronomiaRepository gr;

	@Autowired
	RecensioneRepository rr;

	// SALVA NUOVO UTENTE + ECCEZIONE SE VIENE USATA LA STESSA EMAIL
	public User save(NewUserPayload body) {
		userRepository.findByEmail(body.getEmail()).ifPresent(user -> {
			throw new BadRequestException("L'email " + body.getEmail() + " è gia stata utilizzata");
		});
		User newUser = new User(body.getUsername(), body.getName(), body.getSurname(), body.getEmail(),
				body.getPassword(), body.getNumeroTelefono());
		return userRepository.save(newUser);
	}

	// TORNA LA LISTA DEGLI UTENTI
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	// TORNA LA LISTA DEGLI UTENTI CON L'IMPAGINAZIONE
	public Page<User> find(int page, int size, String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

		return userRepository.findAll(pageable);
	}

	// CERCA UTENTE TRAMITE ID
	public User findById(UUID id) throws NotFoundException {
		return userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	// CERCA E MODIFICA UTENTE TRAMITE ID
	public User findByIdAndUpdate(UUID id, NewUserPayload body) throws NotFoundException {
		User found = this.findById(id);
		found.setName(body.getName());
		found.setSurname(body.getSurname());
		found.setEmail(body.getEmail());
		found.setNumeroTelefono(body.getNumeroTelefono());
		return userRepository.save(found);
	}

	// CERCA E CANCELLA UTENTE TRAMITE ID
	public void findByIdAndDelete(UUID id) throws NotFoundException {
		User found = this.findById(id);
		userRepository.delete(found);
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovato"));
	}

	// PRENDI L'ID DELL'UTENTE LOGGATO
	public User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName = authentication.getName();
		return userRepository.findByEmail(currentUserName)
				.orElseThrow(() -> new NotFoundException("Utente con email " + currentUserName + " non trovato"));
	}

	// -----------METODI PER LE GASTRONOMIE PREFERITE DELL'UTENTE-----------

	// AGGIUNGI GASTRONOMIA AI PREFERITI
	public void addFavoriteGastronomia(UUID gastronomiaId) {
		User user = this.getCurrentUser();
		Gastronomia gastronomia = gr.findById(gastronomiaId)
				.orElseThrow(() -> new NotFoundException("Gastronomia non trovata con l'ID: " + gastronomiaId));

		user.getGastronomie_preferite().add(gastronomia);
		userRepository.save(user);
	}

	// RIMUOVI GASTRONOMIA DAI PREFERITI
	public void removeFavoriteGastronomia(UUID gastronomiaId) {
		User user = this.getCurrentUser();
		Gastronomia gastronomia = gr.findById(gastronomiaId)
				.orElseThrow(() -> new NotFoundException("Gastronomia non trovata con l'ID: " + gastronomiaId));

		user.getGastronomie_preferite().remove(gastronomia);
		userRepository.save(user);
	}

	// TORNA LA LISTA DELLE GASTRONOMIE PREFERITE
	public Page<Gastronomia> getUsergGastronomiePreferite(int page, int size) {
		User currentUser = getCurrentUser();
		Pageable pageable = PageRequest.of(page, size);
		Page<Gastronomia> favorites = userRepository.findFavoriteGastronomiesByUserId(currentUser.getId(), pageable);

		if (favorites.isEmpty()) {
			throw new NotFoundException("La tua lista dei preferiti è vuota");
		}

		return favorites;
	}

	// -----------METODI PER LE PRENOTAZIONI DELLO USER-----------

	// TORNA LA LISTA DELLE PRENOTAZIONI DEL CLIENTE LOGGATO
	public Page<Prenotazione> trovaPrenotazioniPerUtente(int page, int size, String sortBy) {
		User utenteAutenticato = getCurrentUser();
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		Page<Prenotazione> prenotazioni = prenotazioneRepository.findByUserId(utenteAutenticato.getId(), pageable);

		if (prenotazioni.isEmpty()) {
			throw new NotFoundException(
					"Nessuna prenotazione trovata per l'utente con ID: " + utenteAutenticato.getId());
		}

		return prenotazioni;
	}

	// FILTRA PRENOTAZIONI PER DATA SOLO PER L'UTENTE LOGGATO
	public Page<Prenotazione> findUserPrenotazioniByDate(LocalDate dataPrenotazione, int page, int size) {
		User utenteAutenticato = getCurrentUser();
		Pageable pageable = PageRequest.of(page, size);
		Page<Prenotazione> prenotazioni = prenotazioneRepository
				.findByUserIdAndDataPrenotazione(utenteAutenticato.getId(), dataPrenotazione, pageable);

		if (prenotazioni.isEmpty()) {
			throw new NotFoundException("Nessuna prenotazione trovata per l'utente con ID: " + utenteAutenticato.getId()
					+ " alla data: " + dataPrenotazione);
		}

		return prenotazioni;
	}

// ----------METODI PER LE RECENSIONI DELLO USER----------

// TORNA LA LISTA DELLE RECENSIONI DEL CLIENTE LOGGATO
	public Page<Recensione> getUserRecensioni(int page, int size) {
		User currentUser = getCurrentUser();
		Pageable pageable = PageRequest.of(page, size);
		Page<Recensione> userRecensioni = rr.findByUserId(currentUser.getId(), pageable);

		if (userRecensioni.isEmpty()) {
			throw new NotFoundException("Non hai ancora scritto recensioni");
		}

		return userRecensioni;
	}
}
