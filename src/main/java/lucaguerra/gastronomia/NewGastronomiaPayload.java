package lucaguerra.gastronomia;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lucaguerra.enums.TipoGastronomia;

@Data
@AllArgsConstructor
public class NewGastronomiaPayload {

	@NotNull(message = "Il nome è obbligatorio")
	private String nome;
	@NotNull(message = "L'indirizzo è obbligatorio")
	private String indirizzo;
	private String telefono;
	@NotNull(message = "Il prezzo medio è obbligatorio")
	private int prezzoMedio;
	@NotNull(message = "Il tipo di gastronomia è obbligatorio")
	@Enumerated(EnumType.STRING)
	private TipoGastronomia tipoGastronomia;
	private String imageUrl;
}
