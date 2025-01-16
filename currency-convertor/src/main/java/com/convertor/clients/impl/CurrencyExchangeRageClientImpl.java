package com.convertor.clients.impl;

import com.convertor.advices.CurrencyApiResponse;
import com.convertor.clients.CurrencyExchangeRageClient;
import com.convertor.dto.CurrencyExchangeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Slf4j
@Component
@RequiredArgsConstructor
public class CurrencyExchangeRageClientImpl implements CurrencyExchangeRageClient {

    private final RestClient restClient;

    public CurrencyExchangeDto getCurrencyExchangeRate(String baseCurrency, String toConvertCurrency){

        CurrencyApiResponse response = restClient.get()
                //.uri("&currencies={toConvertCurrency}&base_currency={baseCurrency}", toConvertCurrency, baseCurrency)
                .retrieve()
                .body(CurrencyApiResponse.class);
        log.info("Response : {}", response);
        log.info("Response-> getbody() : {}", response.getData());
        return response.getData();
    }
}
