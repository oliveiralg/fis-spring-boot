package fis.login.security.rest;


import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.stereotype.Component;

import fis.login.model.Login;
import fis.login.security.model.JWTToken;


@Component
public class TokenRestRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {

		// @formatter:off
		rest("/security")
			.description("This API get a JWT-Token for Operations.")
			.consumes("application/json")
			.produces("application/json")
		
			.post("/token")
				.outType(JWTToken.class)
				.param()
					.name("SECURITY_USER_EMAIL")
					.type(RestParamType.header)
					.dataType("String")
				.endParam()
				.param()
					.name("SECURITY_USER_PASSWORD")
					.type(RestParamType.header)
					.dataType("String")
				.endParam()
				.route()
					.routeId("rest-obter-token-id")
					.to("direct:obter-token")
				.endRest();
		
		rest("/security")
			.post("/validate")
				.outType(Login.class)
				.route()
					.routeId("rest-validar-token")
					.to("direct:validar-token")
				.endRest();
		// @formatter:on
	}

}
