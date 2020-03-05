package com.backendDevelopment.withtest.dbrestservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.*;
import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "m_paint")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Paint {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id;
    @NotBlank(message = "paint color cannot be null")
    @NonNull String color;
    @NotBlank(message = "paint type cannot be null")
    @NonNull String type;
    @NotNull(message = "volume in litre cannot be null")
    @Min(value=1, message = "volume in litre must not least than 1")
    @NonNull Integer litre;

    @JsonIgnore
    @OneToOne(mappedBy = "paint")
    Item item;
}
