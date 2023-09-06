package lucaguerra.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "menu")
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Menu {

	@Id
	@GeneratedValue
	private UUID id;
	private String nome;

	@OneToMany(mappedBy = "menu", orphanRemoval = true)
	private List<MenuElementi> antipasti = new ArrayList<>();

	@OneToMany(mappedBy = "menu", orphanRemoval = true)
	private List<MenuElementi> primi = new ArrayList<>();

	@OneToMany(mappedBy = "menu", orphanRemoval = true)
	private List<MenuElementi> secondi = new ArrayList<>();

	@OneToMany(mappedBy = "menu", orphanRemoval = true)
	private List<MenuElementi> desserts = new ArrayList<>();

	@OneToMany(mappedBy = "menu", orphanRemoval = true)
	private List<MenuElementi> bevande = new ArrayList<>();

	public Menu(String nome) {

		this.nome = nome;
	}

}
