package com.resevas.reservas;

public class ReservationConverter {
    public static Reservation mapToReservation(ReservationDto reservationDto, User user) {
        Reservation reservation = new Reservation();

        reservation.setUser(user);
        reservation.setDate(reservationDto.getDate());
        reservation.setTime(reservationDto.getTime());
        reservation.setNumberOfPeople(reservationDto.getNumberOfPeople());
        reservation.setState(reservationDto.getState());
        return reservation;
    }

    public static ReservationDto mapToReservationDto(Reservation reservation) {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(reservation.getId());
        reservationDto.setUserId(reservation.getUser().getId());
        reservationDto.setDate(reservation.getDate());
        reservationDto.setTime(reservation.getTime());
        reservationDto.setNumberOfPeople(reservation.getNumberOfPeople());
        reservationDto.setState(reservation.getState());
        return reservationDto;

    }

}
