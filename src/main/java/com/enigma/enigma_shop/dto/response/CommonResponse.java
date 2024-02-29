package com.enigma.enigma_shop.dto.response;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommonResponse<T> {
    private Integer statusCode;
    private String message;
    private T data;
    private PagingResponse paging;
}
