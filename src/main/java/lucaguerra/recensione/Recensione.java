package lucaguerra.recensione;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
@Table(name = "recensione")
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Recensione {

	@Id
	@GeneratedValue
	private UUID id;
	private int valutazione;
	private String commento;
	private LocalDate data;
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	@ManyToOne
	@JoinColumn(name = "gastronomia_id")
	private Gastronomia gastronomia;

	public Recensione(int valutazione, String commento, User user, Gastronomia gastronomia) {

		this.valutazione = valutazione;
		this.commento = commento;
		this.user = user;
		this.gastronomia = gastronomia;
	}

}
