package med.voll.api.infra.security;

import java.security.MessageDigest;

import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		
		return md5(rawPassword.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		
		return md5(rawPassword.toString()).equals(encodedPassword);
	}

	public String md5(String password) {
		if (password == null)
			return null;

		StringBuilder s = new StringBuilder();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] bytes = md.digest();

			for (int i = 0; i < bytes.length; i++) {
				int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
				int parteBaixa = bytes[i] & 0xf;
				if (parteAlta == 0)
					s.append('0');
				s.append(Integer.toHexString(parteAlta | parteBaixa));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return s.toString();
	}
}
