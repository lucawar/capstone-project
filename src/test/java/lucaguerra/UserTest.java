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

import lucaguerra.user.NewUserPayload;

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
		NewUserPayload PayloadNonValido = new NewUserPayload("", "", "", "lucaemail.it", "luca", "39+324345543");

		// PASSARE SEMPRE UN TOKEN VALIDO PER VERIFICARE I TEST
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzODFmMGIyOS0xMzg2LTRiNmEtYTNkNS04ZGE0NzRlOGRhNWEiLCJpYXQiOjE2OTM5NDg1ODIsImV4cCI6MTY5NDU1MzM4Mn0.U4ewQ0PjzmuioONDoV3aZkVbzcfDSf-p7BZXw-ziby0";

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
				"StrongPassword123!", "39+324345543");

		// PASSARE SEMPRE UN TOKEN VALIDO PER VERIFICARE I TEST
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzODFmMGIyOS0xMzg2LTRiNmEtYTNkNS04ZGE0NzRlOGRhNWEiLCJpYXQiOjE2OTM5NDg1ODIsImV4cCI6MTY5NDU1MzM4Mn0.U4ewQ0PjzmuioONDoV3aZkVbzcfDSf-p7BZXw-ziby0";

		MvcResult risultato = mockMvc.perform(post("/users").header("Authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(PayloadValido)))
				.andReturn();

		String rispostaBody = risultato.getResponse().getContentAsString();

		assertThat(rispostaBody).contains("luca.guerra@yahoo.it");
		assertThat(rispostaBody).contains("luca");

	}
}
