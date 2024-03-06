package com.enigma.enigma_shop.service.impl;

import com.enigma.enigma_shop.constant.ResponseMessage;
import com.enigma.enigma_shop.dto.request.UpdateCustomerRequest;
import com.enigma.enigma_shop.entity.Customer;
import com.enigma.enigma_shop.entity.UserAccount;
import com.enigma.enigma_shop.repository.UserAccountRepository;
import com.enigma.enigma_shop.service.CustomerService;
import com.enigma.enigma_shop.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticateUserService {

    private final CustomerService customerService;
    private final UserService userService;

    public boolean hasSameId(UpdateCustomerRequest request){
        Customer currentCustomer = customerService.getById(request.getId());
        UserAccount userAccount = userService.getByContext();
        if (!userAccount.getId().equals(currentCustomer.getUserAccount().getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, ResponseMessage.ERROR_FORBIDDEN);
        }
        return true;
    }

}
