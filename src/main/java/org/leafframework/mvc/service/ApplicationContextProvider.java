package org.leafframework.mvc.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
@Service("ApplicationContextProvider")
@Scope("prototype")
public class ApplicationContextProvider implements ApplicationContextAware {
	private static ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		ApplicationContextProvider.applicationContext = applicationContext;
	}
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
}
