package com.enigma.enigma_shop.dto.request;


import com.enigma.enigma_shop.dto.request.TransactionDetailRequest;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionRequest {
    private String customerId;
    private List<TransactionDetailRequest> transactionDetails;
}
