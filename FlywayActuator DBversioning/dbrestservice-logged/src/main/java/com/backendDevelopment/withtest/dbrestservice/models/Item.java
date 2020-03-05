package com.backendDevelopment.withtest.dbrestservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.*;
import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "t_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id;
    //@Pattern(regexp = "^[\\p{Digit}]{1,4}$",message = "amount must be decimal digit")
    @NotNull(message = "amount cannot be null")
    @NonNull Integer amount;
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "paint_id")
    Paint paint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    Order order;
}
