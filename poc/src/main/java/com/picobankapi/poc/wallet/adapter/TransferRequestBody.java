package com.picobankapi.poc.wallet.adapter;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class TransferRequestBody {
    @Getter
    Long sourceId;
    @Getter
    BigDecimal amount;
    @Getter
    Long targetId;
}
