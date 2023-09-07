package lucaguerra.menu;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lucaguerra.enums.TipoPiatto;

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
	@Enumerated(EnumType.STRING)
	private TipoPiatto tipoPiatto;

	public MenuElementi(String nome, int prezzo, String descrizione, TipoPiatto tipoPiatto, Menu menu) {
		super();
		this.nome = nome;
		this.prezzo = prezzo;
		this.descrizione = descrizione;
		this.tipoPiatto = tipoPiatto;
		this.menu = menu;
	}

}
