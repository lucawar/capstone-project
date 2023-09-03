package lucaguerra.prenotazione;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lucaguerra.gastronomia.Gastronomia;
import lucaguerra.user.User;

@Data
@AllArgsConstructor
public class NewPrenotazionePayload {

	@NotNull(message = "La data è obbligatoria")
	private LocalDate dataPrenotazione;
	@NotNull(message = "La nota è obbligatoria")
	private String nota;
	@NotNull(message = "Lo User è obbligatorio")
	private User user;
	@NotNull(message = "La gastronomia è obbligatoria")
	private Gastronomia Gastronomia;

}
