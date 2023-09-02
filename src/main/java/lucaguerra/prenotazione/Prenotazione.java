package lucaguerra.prenotazione;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lucaguerra.user.User;

@Entity
@Table(name = "prenotazioni")
@Data
@NoArgsConstructor
public class Prenotazione {

	@Id
	@GeneratedValue
	private UUID id;
	private LocalDate dataPrenotazione;
	private String nota;
	private User user;
}
