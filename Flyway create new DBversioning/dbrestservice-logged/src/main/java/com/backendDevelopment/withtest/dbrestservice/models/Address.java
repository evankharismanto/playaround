package com.backendDevelopment.withtest.dbrestservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.NotBlank;
import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "m_address")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Address {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id;
    @NotBlank(message = "address line1 cannot be null")
    @NonNull String line1;
    String line2;
    String line3;

    @JsonIgnore
    @OneToOne(mappedBy = "address")
    Customer customer;

    @JsonIgnore
    @OneToOne(mappedBy = "address")
    Order order;
}
