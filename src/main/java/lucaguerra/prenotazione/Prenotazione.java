package lucaguerra.prenotazione;

import java.time.LocalDate;
import java.time.LocalTime;
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
	private LocalTime oraPrenotazione;
	private String nota;
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
//	@JsonBackReference  //modificato
	private User user;
	@ManyToOne
	@JoinColumn(name = "gastronomia_id", nullable = false)
	private Gastronomia gastronomia;

	public Prenotazione(LocalDate dataPrenotazione, LocalTime oraPrenotazione, String nota, User user,
			Gastronomia gastronomia) {

		this.dataPrenotazione = dataPrenotazione;
		this.oraPrenotazione = oraPrenotazione;
		this.nota = nota;
		this.user = user;
		this.gastronomia = gastronomia;
	}

}
