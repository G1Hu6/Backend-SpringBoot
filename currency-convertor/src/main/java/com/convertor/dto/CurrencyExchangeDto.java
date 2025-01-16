package com.convertor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CurrencyExchangeDto {

    @JsonProperty("USD")
    private Integer usdExchangeRate;
}
