package lucaguerra.prenotazione;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lucaguerra.gastronomia.Gastronomia;
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
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	@ManyToOne
	@JoinColumn(name = "gastronomia_id", nullable = false)
	private Gastronomia Gastronomia;

	public Prenotazione(LocalDate dataPrenotazione, String nota, User user, Gastronomia gastronomia) {

		this.dataPrenotazione = dataPrenotazione;
		this.nota = nota;
		this.user = user;
		Gastronomia = gastronomia;
	}

}
