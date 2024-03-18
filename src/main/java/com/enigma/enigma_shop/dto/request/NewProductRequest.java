package com.enigma.enigma_shop.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewProductRequest {

    @NotBlank(message = "name is required")
    private String name;

    @NotNull(message = "price is required")
    @Min(value = 0, message = "price must be greater than or equal to 0")
    private Long price;

    @NotNull(message = "stock is required")
    @Min(value = 0, message = "stock must be greater than or equal to 0")
    private Integer stock;
}
