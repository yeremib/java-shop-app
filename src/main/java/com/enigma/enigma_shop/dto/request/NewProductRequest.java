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
/*
* Anotasi validasi dari JPA/Hibernate
* 1. @NotBlank -> validasi tidak boleh kosong termasuk whitespace, untuk string
* 2. @NotNull -> tidak boleh null/kosong (number, object)
* 3. @Min -> number untuk menentukan minimal angka yang harus diisi
* 4. @Max -> number untuk menentukan maksimal angka yang harsu diisi
* 5. @Size -> berapa panjang character pada string (parameter min & max)
* 6. @Email -> untuk menentukan email valid atau tidak
* */
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
