package org.leafframework.jws.facade;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.leafframework.jws.facade.adapter.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

@Service("greetingServiceEndpoint")
@WebService(serviceName = "GreetingService")
@SOAPBinding(style = Style.RPC, use = Use.LITERAL)
public class GreetingServiceEndpoint{
	protected Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private GreetingService greetingService;

	@WebMethod
	public String sayHello() {
		return greetingService.sayHello();
	}
}