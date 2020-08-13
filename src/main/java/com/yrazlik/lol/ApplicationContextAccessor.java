package com.yrazlik.lol;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.yrazlik.lol.exception.NoApplicationContextException;

@Component
public final class ApplicationContextAccessor implements ApplicationContextAware {
	
	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationContextAccessor.applicationContext = applicationContext;
	}
	
	public static ApplicationContext get() {
		if(applicationContext == null) {
			throw new NoApplicationContextException();
		}
		return applicationContext;
	}

}
