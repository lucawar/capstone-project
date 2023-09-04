//package lucaguerra;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.List;
//import java.util.Locale;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import com.github.javafaker.Faker;
//
//import lucaguerra.enums.TipoGastronomia;
//import lucaguerra.gastronomia.Gastronomia;
//import lucaguerra.gastronomia.GastronomiaRepository;
//import lucaguerra.prenotazione.Prenotazione;
//import lucaguerra.prenotazione.PrenotazioneRepository;
//import lucaguerra.security.AuthController;
//import lucaguerra.user.NewUserPayload;
//import lucaguerra.user.User;
//import lucaguerra.user.UserRepository;
//
//@Component
//public class Runner implements CommandLineRunner {
//
//	@Autowired
//	GastronomiaRepository gastronomiaRepository;
//
//	@Autowired
//	AuthController ac;
//
//	@Autowired
//	UserRepository us;
//
//	@Autowired
//	PrenotazioneRepository pr;
//
//	@Override
//	public void run(String... args) throws Exception {
//		Faker faker = new Faker(new Locale("it"));
//
//		List<User> utentiDb = us.findAll();
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
//System.out.println("User creati");
//		}
//
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
//	}
//}