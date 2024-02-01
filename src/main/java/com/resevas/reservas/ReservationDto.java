package com.resevas.reservas;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {
    private Long id;
    private Long userId;
    private LocalDate date;
    private LocalTime time;
    private int numberOfPeople;
    private String state;

}
