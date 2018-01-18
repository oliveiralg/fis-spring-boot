package fis.login.security.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import fis.login.model.User;
import fis.login.security.model.JWTToken;
import fis.login.security.service.JWTService;


public class JWTServiceProcessor implements Processor {
	
	private JWTService jwtService; 
	
	public JWTServiceProcessor() {
		jwtService = new JWTService();
	}
	
	@Override
	public void process(Exchange exchange) throws Exception {
		
		User user = exchange.getIn().getBody(User.class);
		JWTToken jwtToken = jwtService.getJWTToken(user);
		exchange.getIn().setBody(jwtToken);
		
	}

}
