package fis.login.common.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import fis.login.security.JWTVerify;

@Component
public class TokenValidationRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("direct:validar-token")
		.routeId("direct-validar-token")
			.process(exchange -> {
				final byte[] sharedSecret = (byte[]) exchange.getIn().getHeader("JWT_SECRET");
				final String JWTToken = exchange.getIn().getHeader("JWT_TOKEN", String.class);
				
				JWTVerify verify = new JWTVerify(sharedSecret, JWTToken);
				
				exchange.setProperty("Login", verify.getLogin());
				exchange.getIn().setHeader("PERMISSIONS", verify.getLogin().getRole());
			})
			.end();
	}

}
