package lucaguerra.recensione;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
public class Recensione {

	@Id
	@GeneratedValue
	private UUID id;
	private int valutazione;
	private String commento;
	@ManyToOne
	@JsonBackReference
	private User user;
	@ManyToOne
	@JsonBackReference
	private Gastronomia gastronomia;

	public Recensione(int valutazione, String commento, User user, Gastronomia gastronomia) {

		this.valutazione = valutazione;
		this.commento = commento;
		this.user = user;
		this.gastronomia = gastronomia;
	}

}
