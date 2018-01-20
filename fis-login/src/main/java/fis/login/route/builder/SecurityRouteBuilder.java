package fis.login.route.builder;

import org.apache.camel.LoggingLevel;

public abstract class SecurityRouteBuilder extends LoggingRouteBuilder {
    @Override
    protected void checkInitialized() throws Exception {
        //@formatter:off
        interceptFrom("^((?!direct).)*$")
            .log(LoggingLevel.DEBUG, "fis.login.route.builder.security", "Validate Token...")
            .to("direct:validar-token");
        //@formatter:on

        super.checkInitialized();
    }
}
