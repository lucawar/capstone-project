package lucaguerra.gastronomia;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lucaguerra.enums.TipoGastronomia;

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
	private String prezzoMedio;
	@Enumerated(EnumType.STRING)
	private TipoGastronomia tipoGastronomia;
	private String imageUrl;

	public Gastronomia(String nome, String indirizzo, String telefono, String prezzoMedio,
			TipoGastronomia tipoGastronomia, String imageUrl) {

		this.nome = nome;
		this.indirizzo = indirizzo;
		this.telefono = telefono;
		this.prezzoMedio = prezzoMedio;
		this.tipoGastronomia = tipoGastronomia;
		this.imageUrl = imageUrl;
	}

}
