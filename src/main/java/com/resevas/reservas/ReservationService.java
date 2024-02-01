package com.resevas.reservas;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReservationService {
    ReservationDto createReservation(ReservationDto reservationDto);

    ReservationDto getReservationById(Long reservationId);

    List<ReservationDto> getAllReservations();

    ReservationDto updateReservation(ReservationDto reservationDto);

    void deleteReservation(Long reservationId);

}