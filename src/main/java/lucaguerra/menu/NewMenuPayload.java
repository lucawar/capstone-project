package lucaguerra.menu;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lucaguerra.gastronomia.Gastronomia;

@Data
@AllArgsConstructor
public class NewMenuPayload {

	@NotNull(message = "La gastronomia è obbligatoria")
	private Gastronomia gastronomia;
	@NotNull(message = "Gli elementi sono obbligatori")
	private List<MenuElementi> elementi = new ArrayList<>();

}
