package lucaguerra.menu;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "menu_elementi")
@Data
@NoArgsConstructor
public class MenuElementi {

	@Id
	@GeneratedValue
	private UUID id;
	private String nome;
	private int prezzo;
	private String descrizione;
	@ManyToOne
	private Menu menu;

	public MenuElementi(String nome, int prezzo, String descrizione, Menu menu) {

		this.nome = nome;
		this.prezzo = prezzo;
		this.descrizione = descrizione;
		this.menu = menu;
	}

}
