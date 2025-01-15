package com.convertor.controllers;

import com.convertor.clients.impl.CurrencyExchangeRageClientImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/convertCurrency")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyExchangeRageClientImpl currencyExchangeClient;

    @GetMapping
    public ResponseEntity<Object> convertCurrency(@RequestParam(defaultValue = "USD") String fromCurrency,
                                                  @RequestParam(defaultValue = "INR") String toCurrency,
                                                  @RequestParam(defaultValue = "1") Integer units){

        return ResponseEntity.ok(currencyExchangeClient.getCurrencyExchangeRate(fromCurrency, toCurrency));
    }
}
