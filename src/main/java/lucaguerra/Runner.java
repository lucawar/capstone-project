package lucaguerra;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import lucaguerra.gastronomia.GastronomiaRepository;
import lucaguerra.menu.MenuElementiRepository;
import lucaguerra.menu.MenuRepository;
import lucaguerra.security.AuthController;
import lucaguerra.user.UserRepository;

@Component
public class Runner implements CommandLineRunner {

	@Autowired
	GastronomiaRepository gastronomiaRepository;

	@Autowired
	AuthController ac;

	@Autowired
	UserRepository ur;

	@Autowired
	MenuRepository mr;

	@Autowired
	MenuElementiRepository mer;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("it"));

		// CREA UTENTI
//		List<User> utentiDb = ur.findAll();
//		if (utentiDb.isEmpty()) {
//
//			for (int i = 0; i < 10; i++) {
//
//				String name = faker.name().firstName();
//				String surname = faker.name().lastName();
//				String username = name.toLowerCase() + "_" + surname.toLowerCase();
//				String email = name.toLowerCase() + "." + surname.toLowerCase() + "@email.com";
//				String password = "1234";
//				String numero = "3456970989";
//				NewUserPayload user = new NewUserPayload(username, name, surname, email, password, numero);
//				ac.saveUser(user);
//
//			}
//			System.out.println("User creati");
//		}

//		// CREA GASTRONOMIE
//		for (int i = 0; i < 10; i++) {
//			String nome = faker.name().fullName();
//			String indirizzo = faker.address().fullAddress();
//			String telefono = faker.phoneNumber().phoneNumber();
//			int prezzoMedio = faker.number().randomDigit();
//			String descrizione = "descrizione defoult da modificare";
//			TipoGastronomia tipoGastronomia = TipoGastronomia.values()[faker.random()
//					.nextInt(TipoGastronomia.values().length)];
//			String imageUrl = "https://www.localiroma.org/wp-content/uploads/2021/12/Pranzo-vista-Colosseo-Roma-10-indirizzi-1.jpg";
//
//			Gastronomia gastronomia = new Gastronomia(nome, indirizzo, telefono, prezzoMedio, descrizione,
//					tipoGastronomia, imageUrl);
//			gastronomiaRepository.save(gastronomia);
//		}
//		System.out.println("Gastronomie create");
//
//		// CREA MENU
//		Menu menuAlfredo = new Menu("Menu 'PERGOLA'");
//		mr.save(menuAlfredo);
//
//		// Crea gli antipasti
//		for (int i = 0; i < 4; i++) {
//			String nomeAntipasto = faker.food().ingredient();
//			String descrizioneAntipasto = faker.lorem().sentence();
//			mer.save(new MenuElementi(nomeAntipasto, faker.number().numberBetween(5, 15), descrizioneAntipasto,
//					TipoPiatto.ANTIPASTI, menuAlfredo));
//		}
//
//		// Crea i primi
//		for (int i = 0; i < 4; i++) {
//			String nomePrimo = faker.food().dish();
//			String descrizionePrimo = faker.lorem().sentence();
//			mer.save(new MenuElementi(nomePrimo, faker.number().numberBetween(10, 20), descrizionePrimo,
//					TipoPiatto.PRIMI, menuAlfredo));
//		}
//
//		// Crea i secondi
//		for (int i = 0; i < 4; i++) {
//			String nomeSecondo = faker.food().sushi();
//			String descrizioneSecondo = faker.lorem().sentence();
//			mer.save(new MenuElementi(nomeSecondo, faker.number().numberBetween(15, 25), descrizioneSecondo,
//					TipoPiatto.SECONDI, menuAlfredo));
//		}
//
//		// Crea i dessert
//		for (int i = 0; i < 4; i++) {
//			String nomeDessert = faker.food().vegetable();
//			String descrizioneDessert = faker.lorem().sentence();
//			mer.save(new MenuElementi(nomeDessert, faker.number().numberBetween(5, 10), descrizioneDessert,
//					TipoPiatto.DESSERT, menuAlfredo));
//		}
//
//		// Crea le bevande
//		for (int i = 0; i < 4; i++) {
//			String nomeBevanda = faker.beer().name();
//			String descrizioneBevanda = faker.lorem().sentence();
//			mer.save(new MenuElementi(nomeBevanda, faker.number().numberBetween(3, 7), descrizioneBevanda,
//					TipoPiatto.BEVANDE, menuAlfredo));
//		}
//
//		System.out.println("Menu e elementi del menu creati");
	}
}