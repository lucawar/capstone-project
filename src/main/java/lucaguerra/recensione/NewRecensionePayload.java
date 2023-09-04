package lucaguerra.recensione;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lucaguerra.gastronomia.Gastronomia;
import lucaguerra.user.User;

@Getter
@Setter
@AllArgsConstructor
public class NewRecensionePayload {

	@NotNull(message = "la Valutazione è obbligatoria")
	private int valutazione;
	@NotNull(message = "Il commento è obbligatorio")
	private String commento;
	@NotNull(message = "Lo user è obbligatorio")
	private User user;
	@NotNull(message = "La gastronomia è obbligatora")
	private Gastronomia gastronomia;

}
