package fis.login.rest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import fis.login.route.builder.SecurityRouteBuilder;

@Component
public class UserInfoRestRoute extends SecurityRouteBuilder{

	@Override
	public void configure() throws Exception {
		
		rest("userinfo").id("user-info")
			.post()
				.produces(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.consumes(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.route().routeId("rest-user-info")
				.to(LOGGING_BEGIN)
				.to("direct:user-info")
				.to(LOGGING_END)
			.endRest();
	}

}
