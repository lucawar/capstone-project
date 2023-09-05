//package lucaguerra;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Locale;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import com.github.javafaker.Faker;
//
//import lucaguerra.enums.TipoCategoriaSezioneMenu;
//import lucaguerra.gastronomia.Gastronomia;
//import lucaguerra.gastronomia.GastronomiaRepository;
//import lucaguerra.menu.Menu;
//import lucaguerra.menu.MenuElementi;
//import lucaguerra.menu.MenuElementiRepository;
//import lucaguerra.menu.MenuRepository;
//import lucaguerra.prenotazione.Prenotazione;
//import lucaguerra.prenotazione.PrenotazioneRepository;
//import lucaguerra.recensione.Recensione;
//import lucaguerra.recensione.RecensioneRepository;
//import lucaguerra.user.User;
//import lucaguerra.user.UserRepository;
//
//@Component
//@Order(2)
//public class RunnerSecond implements CommandLineRunner {
//
//	@Autowired
//	GastronomiaRepository gastronomiaRepository;
//
//	@Autowired
//	PrenotazioneRepository pr;
//
//	@Autowired
//	RecensioneRepository rp;
//
//	@Autowired
//	MenuRepository menuRepository;
//
//	@Autowired
//	MenuElementiRepository menuElementiRepository;
//
//	@Autowired
//	UserRepository ur;
//
//	@Override
//	public void run(String... args) throws Exception {
//
//		List<User> utentiDb = ur.findAll();
//		List<Gastronomia> gastronomieDb = gastronomiaRepository.findAll();
//
//		Faker faker = new Faker(new Locale("it"));
//
//		// Creare Menu e MenuElementi
//		for (Gastronomia gastronomia : gastronomieDb) {
//			// Creare un nuovo Menu per ogni Gastronomia
//			Menu menu = new Menu(gastronomia, new ArrayList<>());
//			menuRepository.save(menu);
//
//			// Creare 5 MenuElementi per ciascun Menu
//			for (int j = 0; j < 5; j++) {
//				String nomeElemento = faker.food().ingredient();
//				int prezzo = faker.number().randomDigitNotZero() * 10;
//				String descrizione = faker.lorem().sentence(5);
//				TipoCategoriaSezioneMenu categoria = TipoCategoriaSezioneMenu.values()[faker.random()
//						.nextInt(TipoCategoriaSezioneMenu.values().length)];
//
//				MenuElementi menuElemento = new MenuElementi(nomeElemento, prezzo, descrizione, categoria, menu);
//				menuElementiRepository.save(menuElemento);
//
//				// Aggiungi l'elemento del menu alla lista di elementi del menu
//				menu.getElementi().add(menuElemento);
//			}
//
//			// Aggiorna il menu con i suoi elementi
////			menuRepository.save(menu);
//		}
//
//		System.out.println("Menu e MenuElementi creati");
//		for (
//
//		User user : utentiDb) {
//			Gastronomia randomGastronomia = gastronomiaRepository.findAll().get(faker.random().nextInt(10));
//			LocalDate randomDate = LocalDate.now().plusDays(faker.random().nextInt(30)); //
//			LocalTime randomTime = LocalTime.of(faker.random().nextInt(24), faker.random().nextInt(60));
//
//			Prenotazione prenotazione = new Prenotazione(randomDate, randomTime, "Nota di prova", user,
//					randomGastronomia);
//			pr.save(prenotazione);
//		}
//
//		System.out.println("Prenotazioni create");
//
//		for (User user : utentiDb) {
//			for (Gastronomia gastronomia : gastronomieDb) {
//				int valutazione = faker.number().numberBetween(1, 5);
//				String commento = faker.lorem().sentence(10);
//
//				Recensione recensione = new Recensione(valutazione, commento, user, gastronomia);
//				rp.save(recensione);
//			}
//		}
//
//		System.out.println("Recensioni create");
//	}
//}
