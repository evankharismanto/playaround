package com.backendDevelopment.withtest.dbrestservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.*;
import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "m_customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id;
    @NotBlank(message = "customer name cannot be null")
    @NonNull String name;
    @NotBlank(message = "phoneNumber cannot be null")
    @Column(name="phone_number")
    @Pattern(regexp = "^[\\p{Digit}]{1,12}$",message = "phoneNumber must be numeric")
    @NonNull String phoneNumber;
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "address_id")
    Address address;

    @JsonIgnore
    @OneToOne(mappedBy = "customer")
    Order order;
}
