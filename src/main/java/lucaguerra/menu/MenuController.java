package lucaguerra.menu;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	MenuService ms;

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Menu saveMenu(@RequestBody @Validated NewMenuPayload body) {
		Menu createdMenu = ms.save(body);
		return createdMenu;
	}

	@GetMapping("")
//	@PreAuthorize("hasAuthority('ADMIN')")
	public Page<Menu> getMenu(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "id") String sortBy) {
		return ms.find(page, size, sortBy);
	}

	@GetMapping("/{menuId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Menu findById(@PathVariable UUID menuId) {
		return ms.findById(menuId);
	}

	@PutMapping("/{menuId}")
	public Menu updateMenu(@PathVariable UUID menuId, @RequestBody NewMenuPayload body) {
		return ms.findByIdAndUpdate(menuId, body);
	}

	@DeleteMapping("/{menuId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePrenotazione(@PathVariable UUID menuId) {
		ms.findByIdAndDelete(menuId);
	}
}
