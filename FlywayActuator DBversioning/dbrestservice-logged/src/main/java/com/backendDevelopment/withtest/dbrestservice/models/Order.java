package com.backendDevelopment.withtest.dbrestservice.models;

import javax.validation.constraints.*;
import javax.persistence.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@Entity
@Table(name = "t_order")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id;
    //@Pattern(regexp = "^\\\\d{4}-\\\\d{2}-\\\\d{2}$",message = "date must be in YYYY-MM-DD")
    @NotNull(message = "date cannot be null")
    @JsonFormat(pattern="yyyy-MM-dd")
    Date date;
    //@Pattern(regexp = "^[05]$",message = "option of delivery must within 0 (No) or 1 (Yes)")
    @NotNull(message = "option of delivery cannot be null")
    @Max(value=1) @Min(value=0,message = "option of delivery must within 0 (No) or 1 (Yes)")
    @NonNull Integer delivery;
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name="address_id")
    Address address;
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name="customer_id")
    Customer customer;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    List<Item> items;
}
