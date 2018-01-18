package fis.login.route;

import java.util.UUID;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import fis.login.model.Login;

@Component
public class LoginRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("direct:login-route")
			.id("direct-login-route")
			.process(exchange -> {
				Login login = new Login();
				login.setUsername(exchange.getIn().getHeader("X-USERNAME", String.class));
				login.setPassword(exchange.getIn().getHeader("X-PASSWORD", String.class));
				
				if (login.getUsername() != null && !login.getUsername().equals("") && login.getPassword() != null && !login.getPassword().equals(""))
				{
					//Faz verificação de autenticação
					
					//Seta token
					login.setToken(UUID.randomUUID().toString());
				}
				exchange.getIn().setBody(login);
			})
			.end();
		
	}

}
