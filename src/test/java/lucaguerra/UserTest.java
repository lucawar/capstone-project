package lucaguerra;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import lucaguerra.payloads.NewUserPayload;

@SpringBootTest
@AutoConfigureMockMvc
public class UserTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@BeforeEach
	public void setup() {
	}

	@Test
	public void testPayloadNonValido() throws Exception {
		NewUserPayload PayloadNonValido = new NewUserPayload("", "", "", "lucaemail.it", "luca");

		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5ZjIxOGQ2Yy1mMzA3LTRmYTQtYjZjNC02NmEyNjg1NjVmZmUiLCJpYXQiOjE2OTM2MDg3NjQsImV4cCI6MTY5NDIxMzU2NH0.nIqeE7FFImI-JLj4xdSSgsmjpEz7ZWoo21URh1GTHe4";

		MvcResult risultato = mockMvc.perform(post("/users").header("Authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(PayloadNonValido)))
				.andReturn();

		String rispostaBody = risultato.getResponse().getContentAsString();

		// MESSAGGI ERRORE SPECIFICI
		assertThat(rispostaBody).contains("Il nome deve avere min 3 caratteri e max 30 caratteri");
		String rispostaBodyUtf8 = new String(rispostaBody.getBytes("ISO-8859-1"), "UTF-8");
		assertThat(rispostaBodyUtf8).contains("La password inserita non è valida");
		assertThat(rispostaBody).contains("La password non soddisfa i requisiti di sicurezza");
	}

	@Test
	public void testValidPayloadShouldReturnCreated() throws Exception {
		NewUserPayload PayloadValido = new NewUserPayload("lucaGuerra", "Luca", "Guerra", "luca.guerra@yahoo.it",
				"StrongPassword123!");

		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5ZjIxOGQ2Yy1mMzA3LTRmYTQtYjZjNC02NmEyNjg1NjVmZmUiLCJpYXQiOjE2OTM2MDg3NjQsImV4cCI6MTY5NDIxMzU2NH0.nIqeE7FFImI-JLj4xdSSgsmjpEz7ZWoo21URh1GTHe4";

		MvcResult risultato = mockMvc.perform(post("/users").header("Authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(PayloadValido)))
				.andReturn();

		String rispostaBody = risultato.getResponse().getContentAsString();

		assertThat(rispostaBody).contains("luca.guerra@yahoo.it");
		assertThat(rispostaBody).contains("luca");

	}
}
