package com.resevas.reservas;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;

}
