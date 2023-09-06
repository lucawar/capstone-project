package lucaguerra.gastronomia;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lucaguerra.enums.TipoGastronomia;
import lucaguerra.menu.Menu;
import lucaguerra.recensione.Recensione;

@Entity
@Table(name = "gastronomia")
@Data
@NoArgsConstructor
public class Gastronomia {

	@Id
	@GeneratedValue
	private UUID id;
	private String nome;
	private String indirizzo;
	private String telefono;
	private int prezzoMedio;
	@Enumerated(EnumType.STRING)
	private TipoGastronomia tipoGastronomia;
	private String imageUrl;
	@OneToMany(mappedBy = "gastronomia", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Recensione> recensioni = new ArrayList<>();
	@OneToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
	private Menu menu;

	public Gastronomia(String nome, String indirizzo, String telefono, int prezzoMedio, TipoGastronomia tipoGastronomia,
			String imageUrl) {

		this.nome = nome;
		this.indirizzo = indirizzo;
		this.telefono = telefono;
		this.prezzoMedio = prezzoMedio;
		this.tipoGastronomia = tipoGastronomia;
		this.imageUrl = imageUrl;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
		menu.setGastronomia(this);
	}
}
