package com.convertor.clients.impl;

import com.convertor.clients.CurrencyExchangeRageClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyExchangeRageClientImpl implements CurrencyExchangeRageClient {

    private final RestClient restClient;

    public ResponseEntity getCurrencyExchangeRate(String baseCurrency, String toConvertCurrency){

        ResponseEntity response = restClient.get()
                .retrieve()
                        .body(ResponseEntity.class);
        log.info("Response : {}", response);
        log.info("Response-> getbody() : {}", response.getBody());
        return response;
    }
}
