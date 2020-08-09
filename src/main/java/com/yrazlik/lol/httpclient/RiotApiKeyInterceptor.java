package com.yrazlik.lol.httpclient;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Component("riotApiKeyInterceptor")
public class RiotApiKeyInterceptor implements Interceptor {
	
	@Value("${riot.apikey}")
	private String riotApiKey;
	
	private static final String RIOT_TOKEN_HEADER = "X-Riot-Token";

	@Override
	public Response intercept(Chain chain) throws IOException {
		Request request = chain.request();
		Request newRequest;
		newRequest = request.newBuilder().addHeader(RIOT_TOKEN_HEADER, riotApiKey)
				.build();
		return chain.proceed(newRequest);
	}

}
