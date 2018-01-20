package fis.login.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import fis.login.model.Login;
import fis.login.model.User;

@Component
public class UserInfoRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("direct:user-info").id("direct-user-info")
			.process(exchange -> {
				Login login = exchange.getProperty("Login", Login.class);
				
				User user = new User();
				user.setEmail("leonardo.oliveira@fabricads.com");
				user.setName("Leonardo Oliveira");
				user.setUsername(login.getUsername());
				user.setToken(login.getToken());
				
				exchange.getIn().setBody(user);		
			})
			.end();
	}
}
