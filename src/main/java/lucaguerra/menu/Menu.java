package lucaguerra.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lucaguerra.gastronomia.Gastronomia;

@Entity
@Table(name = "menu")
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Menu {

	@Id
	@GeneratedValue
	private UUID id;
	@OneToOne(mappedBy = "menu")
//	@JsonBackReference
	private Gastronomia gastronomia;
	@OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
//	@JsonManagedReference //modificato
	private List<MenuElementi> elementi = new ArrayList<>();

	public Menu(Gastronomia gastronomia, List<MenuElementi> elementi) {

		this.gastronomia = gastronomia;
		this.elementi = elementi;
	}

}
