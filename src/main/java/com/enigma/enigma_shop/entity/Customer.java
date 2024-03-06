package com.enigma.enigma_shop.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="m_customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "mobile_phone_no")
    private String mobilePhoneNo;

    @Column(name = "address")
    private String address;

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Jakarta")
    private Date birthdate;

    @Column(name = "status")
    private Boolean status;

    @OneToOne
    @JoinColumn(name = "user_account_id", unique = true)
    private UserAccount userAccount;
}
