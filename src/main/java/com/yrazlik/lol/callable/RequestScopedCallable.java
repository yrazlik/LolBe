package com.yrazlik.lol.callable;

import java.util.concurrent.Callable;

public abstract class RequestScopedCallable<T> extends RequestScopeProcessor implements Callable<T> {
	
	@Override
	public T call() throws Exception {
		beforeExecute();
		try {
			return execute();
		} catch (Exception e) {
			throw e;
		} finally {
			afterExecute();
		}
	}
	
	public abstract T execute();

}
