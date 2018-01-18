package fis.login.rest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import fis.login.route.builder.LoggingRouteBuilder;

@Component
public class LoginRestRoute extends LoggingRouteBuilder{

	@Override
	public void configure() throws Exception {
		
		rest("login").id("login-route")
			.get()
				.produces(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.consumes(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.route().routeId("rest-login-route")
				.to(LOGGING_BEGIN)
				.to("direct:login-route")
				.to(LOGGING_END)
			.endRest();
	}
}
