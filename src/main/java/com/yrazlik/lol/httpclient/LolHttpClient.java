package com.yrazlik.lol.httpclient;

import java.io.IOException;
import java.time.Duration;
import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.yrazlik.lol.response.RiotApiResponse;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Component("lolHttpClient")
public class LolHttpClient {
	
	private static final Logger LOGGER = Logger.getLogger(LolHttpClient.class);

	@Value("${httpclient.connecttimeout}")
	private long connectTimeout;

	@Value("${httpclient.readtimeout}")
	private long readTimeout;
	
	@Autowired
	private RiotApiKeyInterceptor riotApiKeyInterceptor;

	private OkHttpClient okHttpClient;

	@PostConstruct
	private void initHttpClient() {
		okHttpClient = new OkHttpClient();
		okHttpClient = okHttpClient.newBuilder().addInterceptor(riotApiKeyInterceptor)
				.connectTimeout(Duration.ofMillis(connectTimeout))
				.readTimeout(Duration.ofMillis(readTimeout)).retryOnConnectionFailure(true)
				.build();
	}

	public RiotApiResponse makeGetRequest(String url) {
		RiotApiResponse riotApiResponse = new RiotApiResponse();
		Request request = new Request.Builder()
                .url(url)
                .build();
		Response response;
		String body = null;
		try {
			response = okHttpClient.newCall(request).execute();
			riotApiResponse.setStatusCode(response.code());
			body = response.body().string();
			riotApiResponse.setBody(body);
		} catch (IOException e) {
			riotApiResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			LOGGER.error("Error parsing GET request response for url: " + url, e);
		}
		
		if(riotApiResponse.getStatusCode() != HttpStatus.OK.value()) {
			String errMsg = (body != null ? body : "Riot Api returned non 200");
			throw new RuntimeException(errMsg);
		}
		return riotApiResponse;
	}
	
	
}
