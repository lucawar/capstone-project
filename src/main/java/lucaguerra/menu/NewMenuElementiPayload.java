package lucaguerra.menu;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lucaguerra.enums.TipoPiatto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewMenuElementiPayload {

	@NotNull(message = "Il nome è obbligatorio")
	private String nome;
	@NotNull(message = "Il prezzo è obbligatorio")
	private int prezzo;
	@NotNull(message = "La descrizione è obbligatoria")
	private String descrizione;
	@NotNull(message = "Il tipo è obbligatorio")
	private TipoPiatto tipoPiatto;
	@NotNull(message = "Il menu è obbligatorio")
	private Menu menu;
}
