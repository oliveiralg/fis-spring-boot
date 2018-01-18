package fis.login.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import fis.login.security.JWTVerify;

@Component
public class TokenValidationRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("direct:token-validation")
			.id("direct-token-validation")
			.process(exchange -> {
				final byte[] sharedSecret = (byte[]) exchange.getIn().getHeader("JWT_SECRET");
				final String JWTToken = exchange.getIn().getHeader("JWT_Token", String.class);
				
				JWTVerify verify = new JWTVerify(sharedSecret, JWTToken);
				
				exchange.setProperty("User", verify.getUser());
				exchange.getIn().setHeader("PERMISSIONS", verify.getUser().getRole());
			})
			.end();
	}

}
