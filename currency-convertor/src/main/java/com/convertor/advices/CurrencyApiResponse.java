package com.convertor.advices;

import com.convertor.dto.CurrencyExchangeDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyApiResponse{

    private CurrencyExchangeDto data;
}
