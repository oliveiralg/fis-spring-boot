package fis.login.security.model;

public class JWTToken {

	private byte[] sharedSecret;
	
	private String jwtToken;

	public byte[] getSharedSecret() {
		return sharedSecret;
	}

	public void setSharedSecret(byte[] sharedSecret) {
		this.sharedSecret = sharedSecret;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

}