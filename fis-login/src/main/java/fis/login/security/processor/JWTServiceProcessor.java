package fis.login.security.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import fis.login.model.Login;
import fis.login.security.model.JWTToken;
import fis.login.security.service.JWTService;

public class JWTServiceProcessor implements Processor {
	
	private JWTService jwtService; 
	
	public JWTServiceProcessor() {
		jwtService = new JWTService();
	}
	
	@Override
	public void process(Exchange exchange) throws Exception {
		
		Login login = exchange.getIn().getBody(Login.class);
		JWTToken jwtToken = jwtService.getJWTToken(login);
		exchange.getIn().setBody(jwtToken);
		
	}

}
