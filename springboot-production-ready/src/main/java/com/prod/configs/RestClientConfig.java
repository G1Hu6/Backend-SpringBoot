package com.prod.configs;

import com.prod.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;

import java.net.http.HttpHeaders;
import java.rmi.RemoteException;
import java.util.function.Consumer;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Configuration
public class RestClientConfig {

    @Value("${student.service.base.url}")
    private String BASE_URL;

    @Bean
    @Qualifier("studentRestClient")
    public RestClient getStudentRestServiceClient(){
        RestClient restClient = RestClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader(CONTENT_TYPE,APPLICATION_JSON_VALUE)
                .defaultStatusHandler(HttpStatusCode::is5xxServerError,(request, response) -> {

                    throw new RemoteException("Internal Server Error");
                })
                .build();
        return restClient;
    }
}
