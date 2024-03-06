package com.enigma.enigma_shop.dto.response;

import lombok.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
    private String username;
    private String token;
    private List<String> roles;
}
