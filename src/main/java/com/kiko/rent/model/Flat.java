package com.kiko.rent.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Flat {
    private Long id;
    @NotNull(message = "address is compulsory")
    @NotBlank(message = "address is compulsory")
    @Size(max = 255,message = "maximum size of address is 255")
    private String address;
    private Long userId;
}
