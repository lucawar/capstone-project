package lucaguerra.payloads;

import lombok.Getter;

@Getter
public class UserLoginPayload {
	String email;
	String password;
}
