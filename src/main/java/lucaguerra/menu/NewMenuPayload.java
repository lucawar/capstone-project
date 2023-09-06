package lucaguerra.menu;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewMenuPayload {

	@NotNull(message = "Il nome è obbligatorio")
	private String nome;

}
