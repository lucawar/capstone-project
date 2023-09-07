package lucaguerra.prenotazione;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lucaguerra.gastronomia.Gastronomia;

@Getter
@Setter
@AllArgsConstructor
public class NewUserPrenotazioniPayload {

	@NotNull(message = "La data è obbligatoria")
	private LocalDate dataPrenotazione;
	@NotNull(message = "L'orario è obbligatorio")
	private LocalTime oraPrenotazione;
	@NotNull(message = "La nota è obbligatoria")
	private String nota;
	@NotNull(message = "La gastronomia è obbligatoria")
	private Gastronomia Gastronomia;
}
