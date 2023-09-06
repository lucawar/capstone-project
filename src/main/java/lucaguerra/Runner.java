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
//			TipoGastronomia tipoGastronomia = TipoGastronomia.values()[faker.random()
//					.nextInt(TipoGastronomia.values().length)];
//			String imageUrl = faker.internet().avatar();
//
//			Gastronomia gastronomia = new Gastronomia(nome, indirizzo, telefono, prezzoMedio, tipoGastronomia,
//					imageUrl);
//			gastronomiaRepository.save(gastronomia);
//		}
//		System.out.println("Gastronomie create");
//
//		// CREA MENU
//		Menu menuOsteriaMimmo = new Menu("Menu Osteria Mimmo");
//		mr.save(menuOsteriaMimmo);
//
//		// Aggiungi manualmente gli elementi per ciascuna lista
//		MenuElementi antipasto1 = new MenuElementi("Antipasto 1", 10, "Descrizione antipasto 1", menuOsteriaMimmo);
//		mer.save(antipasto1);
//		menuOsteriaMimmo.getAntipasti().add(antipasto1);
//
//		MenuElementi antipasto2 = new MenuElementi("Antipasto 2", 12, "Descrizione antipasto 2", menuOsteriaMimmo);
//		mer.save(antipasto2);
//		menuOsteriaMimmo.getAntipasti().add(antipasto2);
//
//		System.out.println("Menu e elementi del menu creati");
	}
}
