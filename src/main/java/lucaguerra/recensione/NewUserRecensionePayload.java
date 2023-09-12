package lucaguerra.recensione;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lucaguerra.gastronomia.Gastronomia;

@Getter
@Setter
@AllArgsConstructor
public class NewUserRecensionePayload {

	@NotNull(message = "La valutazione è obbligatoria")
	private int valutazione;
	@NotNull(message = "Il commento è obbligatorio")
	private String commento;
	private Gastronomia gastronomia;
}
