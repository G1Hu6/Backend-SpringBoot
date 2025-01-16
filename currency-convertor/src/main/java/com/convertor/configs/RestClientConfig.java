package com.convertor.configs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@Configuration
public class RestClientConfig {

    @Value("app.freeCurrency.api.url")
    private String BASE_URL;

    @Value("app.freeCurrency.api.key")
    private String API_KEY;

    @Bean
    @Qualifier("getRestClient")
    public RestClient getRestClient(){
        String url = BASE_URL + "?apiKey=" + API_KEY + "&currencies=USD&base_currency=INR";
        log.info("Api url : {}", url);
        return RestClient.builder()
                .baseUrl(url)
                .defaultHeader(CONTENT_TYPE,APPLICATION_JSON_VALUE)
                .build();
    }
}
