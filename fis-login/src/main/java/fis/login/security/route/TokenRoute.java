package fis.login.security.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import fis.login.model.Login;
import fis.login.security.processor.JWTServiceProcessor;

@Component
public class TokenRoute extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {

		from("direct:obter-token")
			.routeId("direct-obter-token")
			.unmarshal().json(JsonLibrary.Jackson, Login.class)
			.process(new JWTServiceProcessor())
			.removeHeaders("security_user*")
		.end();
	}

}