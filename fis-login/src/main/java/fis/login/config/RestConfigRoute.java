package fis.login.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestPropertyDefinition;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RestConfigRoute extends RouteBuilder {

    @Bean
    public ServletRegistrationBean camelServletRegistrationBean() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new CamelHttpTransportServlet(),
                "/r/api/v1/*");
        registration.setName("CamelServlet");
        return registration;
    }

    @Override
    public void configure() throws Exception {
        //@formatter:off
        restConfiguration()
            .dataFormatProperty("prettyPrint", "true")
            .component("servlet")
            .bindingMode(RestBindingMode.json)
            .apiContextPath("/api-doc")
                    .apiProperty("api.title", "fis-login").apiProperty("api.version", "1.0.0")
                    .apiProperty("cors", "true")
            .enableCORS(true)
            .setCorsHeaders(corsHeaders());
        //@formatter:on
    }
    
    public List<RestPropertyDefinition> corsHeaders(){
    	
    	List<RestPropertyDefinition> corsHeaders = new ArrayList<RestPropertyDefinition>();
    	RestPropertyDefinition rPD = new RestPropertyDefinition();
    	rPD = new RestPropertyDefinition();
    	rPD.setKey("Access-Control-Allow-Headers");
    	rPD.setValue("Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization");
    	
    	corsHeaders.add(rPD);
    	
    	return corsHeaders;
    }

}