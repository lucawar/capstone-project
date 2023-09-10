package lucaguerra.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lucaguerra.enums.Role;
import lucaguerra.gastronomia.Gastronomia;
import lucaguerra.prenotazione.Prenotazione;
import lucaguerra.recensione.Recensione;

@SuppressWarnings("serial")
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User implements UserDetails {

	@Id
	@GeneratedValue
	private UUID id;
	@SuppressWarnings("unused")
	private String username;
	private String name;
	private String surname;
	@Column(nullable = false, unique = true)
	private String email;
	@JsonIgnore
	private String password;
	private String numeroTelefono;
	@Enumerated(EnumType.STRING)
	private Role role;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Prenotazione> prenotazioni = new ArrayList<>();
	@ManyToMany
	@JoinTable(name = "user_gastronomia_preferito", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "gastronomia_id"))
	private List<Gastronomia> gastronomie_preferite = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Recensione> recensioni = new ArrayList<>();

	@SuppressWarnings("static-access")
	public User(String username, String name, String surname, String email, String password, String numeroTelefono) {

		this.username = username;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.role = role.USER;
		this.numeroTelefono = numeroTelefono;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (this.role != null) {
			return List.of(new SimpleGrantedAuthority(role.name()));
		}

		return Collections.emptyList();
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
