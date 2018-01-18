package fis.login.route.builder;

import org.apache.camel.builder.RouteBuilder;

public abstract class LoggingRouteBuilder extends RouteBuilder {
	
    public static final String LOGGING_BEGIN = "direct:log-wiretap-begin";
    public static final String LOGGING_END = "direct:log-wiretap-end";
    public static final String LOGGING_MSG = "direct:log-wiretap-msg";
    
}
