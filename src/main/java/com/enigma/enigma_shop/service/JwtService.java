package com.enigma.enigma_shop.service;

import com.enigma.enigma_shop.dto.response.JwtClaims;
import com.enigma.enigma_shop.entity.UserAccount;

public interface JwtService {
    public String generateToken(UserAccount userAccount);
    boolean verifyJwtToken(String bearerToken);
    JwtClaims getClaimsByToken(String token);
}
