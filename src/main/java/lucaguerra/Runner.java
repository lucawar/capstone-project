//package lucaguerra;
//
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
//import lucaguerra.enums.TipoGastronomia;
//import lucaguerra.gastronomia.Gastronomia;
//import lucaguerra.gastronomia.GastronomiaRepository;
//import lucaguerra.security.AuthController;
//import lucaguerra.user.NewUserPayload;
//import lucaguerra.user.User;
//import lucaguerra.user.UserRepository;
//
//@Component
//@Order(1)
//public class Runner implements CommandLineRunner {
//
//	@Autowired
//	GastronomiaRepository gastronomiaRepository;
//
//	@Autowired
//	AuthController ac;
//
//	@Autowired
//	UserRepository ur;
//
//	@Override
//	public void run(String... args) throws Exception {
//		Faker faker = new Faker(new Locale("it"));
//
//		// CREA UTENTI
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
//
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
//	}
//}