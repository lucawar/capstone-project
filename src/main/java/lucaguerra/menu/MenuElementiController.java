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
@RequestMapping("/menuElementi")
public class MenuElementiController {

	@Autowired
	MenuElementiService mes;

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public MenuElementi saveMenuElementi(@RequestBody @Validated NewMenuElementiPayload body) {
		MenuElementi createdMenuElementi = mes.save(body);
		return createdMenuElementi;
	}

	@GetMapping("")
	public Page<MenuElementi> getMenuElementi(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return mes.find(page, size, sortBy);
	}

	@GetMapping("/{menuElementiId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public MenuElementi findById(@PathVariable UUID menuElementiId) {
		return mes.findById(menuElementiId);
	}

	@PutMapping("/{menuElementiId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public MenuElementi updateMenuElementi(@PathVariable UUID menuElementiId,
			@RequestBody NewMenuElementiPayload body) {
		return mes.findByIdAndUpdate(menuElementiId, body);
	}

	@DeleteMapping("/{menuElementiId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteMenuElementi(@PathVariable UUID menuElementiId) {
		mes.findByIdAndDelete(menuElementiId);
	}
}
