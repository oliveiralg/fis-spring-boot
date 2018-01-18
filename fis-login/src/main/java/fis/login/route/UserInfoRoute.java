package fis.login.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import fis.login.model.User;

@Component
public class UserInfoRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("direct:user-info").id("direct-user-info")
			.process(exchange -> {
				User user = exchange.getProperty("User", User.class);
				user.setEmail("leonardo.oliveira@fabricads.com");
				user.setName("Leonardo Oliveira");
				
				exchange.getIn().setBody(user);		
			})
			.end();
	}
}
