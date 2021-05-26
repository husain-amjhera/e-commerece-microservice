package com.yash.ecom.shippingcharge.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
	
	 @Bean
	    public ServletRegistrationBean <MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {
	        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
	        servlet.setApplicationContext(context);
	        servlet.setTransformWsdlLocations(true);
	        return new ServletRegistrationBean<>(servlet, "/soapWS/*");
	    }


	    @Bean
	    public XsdSchema shippingChargeSchema() {
	        return new SimpleXsdSchema(new ClassPathResource("shippingCharge.xsd"));
	    }

	    @Bean(name ="shippingCharge" )
	    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema shippingChargeSchema) {

	        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
	        definition.setSchema(shippingChargeSchema);
	        definition.setLocationUri("/soapWS");
	        definition.setPortTypeName("ShippingChargeServicePort");
	        definition.setTargetNamespace("http://yash.com/ecom/shippingCharge");
	        return definition;
	    }

}
