package lucaguerra.menu;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuElementiRepository extends JpaRepository<MenuElementi, UUID> {

}
