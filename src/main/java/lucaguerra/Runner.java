//package lucaguerra;
//
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
//
//@Component
//public class Runner implements CommandLineRunner {
//
//	@Autowired
//	GastronomiaRepository gastronomiaRepository;
//
//	@Override
//	public void run(String... args) throws Exception {
//		Faker faker = new Faker(new Locale("it"));
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
//	}
//}