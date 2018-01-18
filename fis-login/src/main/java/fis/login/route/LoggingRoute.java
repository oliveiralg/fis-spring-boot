package fis.login.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class LoggingRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		from("direct:log-wiretap-begin")
			.id("direct-log-wiretap-begin")
			.process(exchange -> {
				Long startTime = System.currentTimeMillis();
				exchange.setProperty("startTime", startTime);
			})
			.to("direct:log-wiretap-msg")
		.end();
		
		from("direct:log-wiretap-end")
			.id("direct-log-wiretap-end")
			.to("direct:log-wiretap-msg")
		.end();
		
		from("direct:log-wiretap-msg")
			.id("direct-log-wiretap-msg")
			.to("direct:write-log")
		.end();
		
		from("direct:write-log")
			.id("direct-write-log")
			.process(exchange -> {
				Long startTime = exchange.getProperty("startTime", Long.class);
		        if (startTime != null) {
		        	long timeSpent = System.currentTimeMillis() - startTime;
		        	exchange.setProperty("timeSpent", timeSpent);
		        }
			})
			.choice()
				.when().simple("${exchangeProperty[hasErrors]} != null")
					.log(LoggingLevel.ERROR, "logFile", "${body}")
				.otherwise()
					.log(LoggingLevel.INFO, "logFile", "${body}")
			.end()
			.log(LoggingLevel.DEBUG, "fis.login.logging", "${body}")
		.end();
	}

}
