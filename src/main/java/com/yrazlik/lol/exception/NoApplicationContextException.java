package com.yrazlik.lol.exception;

import org.springframework.beans.FatalBeanException;

public class NoApplicationContextException extends FatalBeanException {

	public NoApplicationContextException(String msg) {
		super(msg);
	}
	
	public NoApplicationContextException() {
		super("Application context is null");
	}

}
