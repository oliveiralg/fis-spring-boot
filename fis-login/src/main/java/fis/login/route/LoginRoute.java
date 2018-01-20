package fis.login.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import fis.login.processor.LoginProcessor;

@Component
public class LoginRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("direct:login-route")
			.id("direct-login-route")
			.process(new LoginProcessor())
			.end();
	}

}
