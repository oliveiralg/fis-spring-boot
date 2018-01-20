package fis.login.security;

import java.text.ParseException;
import java.util.Date;

import com.google.gson.Gson;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;

import fis.login.model.Login;

public class JWTVerify {
	
	private Login login;
	
	public JWTVerify() {
	}
	
	public JWTVerify(final byte[] sharedSecret, final String JWTToken) throws JOSEException, ParseException, SecurityException {
		verify(sharedSecret, JWTToken);
	}

	private void verify(byte[] sharedSecret, String JWTToken) throws JOSEException, ParseException, SecurityException {

		// On the consumer side, parse the JWS and verify its HMAC
		SignedJWT signedJWT = SignedJWT.parse(JWTToken);

		JWSVerifier verifier = new MACVerifier(sharedSecret);

		if (!signedJWT.verify(verifier)) {
			throw new SecurityException();
		}

		this.login = new Gson().fromJson(signedJWT.getPayload().toString(), Login.class);

		if (new Date().getTime() > this.login.getExpiration()) {
			throw new SecurityException();
		}
	}
	
	public Login getLogin() {
		return login;
	}

}