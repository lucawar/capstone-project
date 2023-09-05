package lucaguerra.menu;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lucaguerra.enums.TipoCategoriaSezioneMenu;

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
	@Enumerated(EnumType.STRING)
	private TipoCategoriaSezioneMenu categoria;
	@ManyToOne
	@JsonBackReference
	private Menu menu;

	public MenuElementi(String nome, int prezzo, String descrizione, TipoCategoriaSezioneMenu categoria, Menu menu) {

		this.nome = nome;
		this.prezzo = prezzo;
		this.descrizione = descrizione;
		this.categoria = categoria;
		this.menu = menu;
	}

}
