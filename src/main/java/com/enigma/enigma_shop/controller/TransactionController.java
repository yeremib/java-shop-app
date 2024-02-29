package com.enigma.enigma_shop.controller;

import com.enigma.enigma_shop.constant.APIUrl;
import com.enigma.enigma_shop.dto.request.TransactionRequest;
import com.enigma.enigma_shop.dto.response.TransactionResponse;
import com.enigma.enigma_shop.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.TRANSACTION_API)
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponse> createNewTransaction(@RequestBody TransactionRequest request) {
        TransactionResponse transactionResponse = transactionService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionResponse);
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getAllTransaction() {
        List<TransactionResponse> transactionResponses = transactionService.getAll();
        return ResponseEntity.ok(transactionResponses);
    }
}
