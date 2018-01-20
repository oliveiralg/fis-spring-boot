package fis.login.security.service;

import java.security.SecureRandom;
import java.text.ParseException;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTClaimsSet.Builder;
import com.nimbusds.jwt.SignedJWT;

import fis.login.model.Login;
import fis.login.security.model.JWTToken;

@Component
public class JWTService {

	public JWTToken getJWTToken(final Login login) throws JOSEException, ParseException {

		// Generate random 256-bit (32-byte) shared secret
		SecureRandom random = new SecureRandom();
		byte[] sharedSecret = new byte[32];
		random.nextBytes(sharedSecret);

		// Create HMAC signer
		JWSSigner signer = new MACSigner(sharedSecret);

		// Prepare JWT with claims set
		JWTClaimsSet claimsSet;

		JWTClaimsSet.Builder builder = new Builder();

		builder.claim("PASSWORD", login.getPassword());
		builder.subject(login.getUsername());
		builder.expirationTime(getExpirationTime());

		claimsSet = builder.build();

		SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);

		// Apply the HMAC protection
		signedJWT.sign(signer);

		// create a Object JWTToken with the token and the sharedSecret
		JWTToken jwtToken = new JWTToken();
		
		jwtToken.setJwtToken(signedJWT.serialize());
		jwtToken.setSharedSecret(sharedSecret);
		
		return jwtToken;

	}

	private Date getExpirationTime() {
		Long timeToSpend = new Date().getTime() + (1000 * 60 * 60 * 24);
		return new Date(timeToSpend);
	}

}
