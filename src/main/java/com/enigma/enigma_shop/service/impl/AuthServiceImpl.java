package com.enigma.enigma_shop.service.impl;

import com.enigma.enigma_shop.constant.UserRole;
import com.enigma.enigma_shop.dto.request.AuthRequest;
import com.enigma.enigma_shop.dto.response.LoginResponse;
import com.enigma.enigma_shop.dto.response.RegisterResponse;
import com.enigma.enigma_shop.entity.Customer;
import com.enigma.enigma_shop.entity.Role;
import com.enigma.enigma_shop.entity.UserAccount;
import com.enigma.enigma_shop.repository.UserAccountRepository;
import com.enigma.enigma_shop.security.SecurityConfiguration;
import com.enigma.enigma_shop.service.AuthService;
import com.enigma.enigma_shop.service.CustomerService;
import com.enigma.enigma_shop.service.JwtService;
import com.enigma.enigma_shop.service.RoleService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl  implements AuthService {
    private final UserAccountRepository userAccountRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final CustomerService customerService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Value("${enigma_shop.username.superadmin}")
    private String superAdminUsername;
    @Value("${enigma_shop.password.superadmin}")
    private String superAdminPassword;

    @Transactional(rollbackFor = Exception.class)
    @PostConstruct
    public void initSuperAdmin() {
        Optional<UserAccount> currUser = userAccountRepository.findByUsername(superAdminUsername);
        if (currUser.isPresent()) return;

        Role superAdmin = roleService.getOrSave(UserRole.ROLE_SUPER_ADMIN);
        Role admin = roleService.getOrSave(UserRole.ROLE_ADMIN);
        Role customer = roleService.getOrSave(UserRole.ROLE_CUSTOMER);

        UserAccount account = UserAccount.builder()
                .username("superadmin")
                .password(passwordEncoder.encode(superAdminPassword))
                .role(List.of(superAdmin, admin, customer))
                .isEnable(true)
                .build();

        userAccountRepository.save(account);
    }

    @Override
    public RegisterResponse register(AuthRequest request)  throws DataIntegrityViolationException {

            Role role = roleService.getOrSave(UserRole.ROLE_CUSTOMER);
            String hashPassword = passwordEncoder.encode(request.getPassword());

            UserAccount account = UserAccount.builder()
                    .username(request.getUsername())
                    .password(hashPassword)
                    .role(List.of(role))
                    .isEnable(true)
                    .build();

            userAccountRepository.saveAndFlush(account);

            Customer customer = Customer.builder()
                    .status(true)
                    .userAccount(account)
                    .build();
            customerService.create(customer);

            List<String> roles = account.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
            return RegisterResponse.builder()
                    .username(account.getUsername())
                    .roles(roles)
                    .build();

    }

    @Override
    public RegisterResponse registerAdmin(AuthRequest request) {
        return null;
    }

    @Override
    public LoginResponse login(AuthRequest request) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        );
        Authentication authenticate = authenticationManager.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        UserAccount userAccount = (UserAccount) authenticate.getPrincipal();
        String token = jwtService.generateToken(userAccount);
        return LoginResponse.builder()
                .username(request.getUsername())
                .roles(userAccount.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .token(token)
                .build();
    }
}
