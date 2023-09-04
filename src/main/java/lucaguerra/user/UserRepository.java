package lucaguerra.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import lucaguerra.gastronomia.Gastronomia;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

	Optional<User> findByEmail(String email);

	@Query("SELECT g FROM User u JOIN u.gastronomie_preferite g WHERE u.id = ?1")
	Page<Gastronomia> findFavoriteGastronomiesByUserId(UUID userId, Pageable pageable);
}
