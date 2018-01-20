package fis.login.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import fis.login.model.Login;
import fis.login.security.model.JWTToken;
import fis.login.security.service.JWTService;

public class LoginProcessor implements Processor {

	private JWTService jwtService; 
	
	public LoginProcessor() {
		jwtService = new JWTService();
	}
	
	@Override
	public void process(Exchange exchange) throws Exception {
		
		String username = exchange.getIn().getHeader("X-USERNAME", String.class);
		String password = exchange.getIn().getHeader("X-PASSWORD", String.class);
		//Verifica autenticação e pega role do usuário
		
		
		//Se autenticação está OK
		Login login = new Login();
		login.setUsername(username);
		login.setPassword(password);
		login.setRole("ADMIN");
		//Passa para o Serviço JWT para criar token
		JWTToken jwtToken = jwtService.getJWTToken(login);
		
		//Joga no login o token
		login.setToken(jwtToken.getJwtToken());
		login.setSecret(jwtToken.getSharedSecret());
		
		exchange.getIn().setBody(login);
	}

}
