package lucaguerra.menu;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lucaguerra.enums.TipoCategoriaSezioneMenu;

@Data
@AllArgsConstructor
public class NewMenuElementiPayload {

	@NotNull(message = "Il nome è obbligatorio")
	private String nome;
	@NotNull(message = "Il prezzo è obbligatorio")
	private int prezzo;
	@NotNull(message = "La descrizione è obbligatoria")
	private String descrizione;
	@NotNull(message = "Il tipo di categoria è obbligatoria")
	private TipoCategoriaSezioneMenu categoria;
	@NotNull(message = "Il menu è obbligatoria")
	private Menu menu;
}
