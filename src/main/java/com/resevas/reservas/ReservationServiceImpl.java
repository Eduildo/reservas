package com.resevas.reservas;

import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(UserRepository userRepository, ReservationRepository reservationRepository) {
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public ReservationDto createReservation(ReservationDto reservationDto) {
        Optional<User> userOptional = userRepository.findById(reservationDto.getUserId());
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User with id " + reservationDto.getUserId() + " not found");
        }

        Reservation reservation = ReservationConverter.mapToReservation(reservationDto, userOptional.get());
        Reservation savedReservation = reservationRepository.save(reservation);
        return ReservationConverter.mapToReservationDto(savedReservation);

    }

    private ReservationDto convertToReservationDto(Reservation reservation) {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(reservation.getId());
        reservationDto.setUserId(reservation.getUser().getId());
        reservationDto.setDate(reservation.getDate());
        reservationDto.setTime(reservation.getTime());
        reservationDto.setNumberOfPeople(reservation.getNumberOfPeople());
        reservationDto.setState(reservation.getState());
        return reservationDto;
    }

    @Override
    public ReservationDto getReservationById(Long reservationId) {
        return reservationRepository.findById(reservationId)
                .map(this::convertToReservationDto)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found with id: " + reservationId));
    }

    @Override
    public List<ReservationDto> getAllReservations() {
        List<Reservation> allReservations = reservationRepository.findAll();
        return allReservations.stream()
                .map(this::convertToReservationDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationDto updateReservation(ReservationDto reservationDto) {

        Optional<Reservation> existingReservationOptional = reservationRepository.findById(reservationDto.getId());
        if (existingReservationOptional.isEmpty()) {
            throw new IllegalArgumentException("Reservation with id " + reservationDto.getId() + " not found");
        }

        Reservation existingReservation = existingReservationOptional.get();
        existingReservation.setDate(reservationDto.getDate());
        existingReservation.setTime(reservationDto.getTime());
        existingReservation.setNumberOfPeople(reservationDto.getNumberOfPeople());
        existingReservation.setState(reservationDto.getState());

        Reservation updatedReservation = reservationRepository.save(existingReservation);

        return convertToReservationDto(updatedReservation);

    }

    @Override
    public void deleteReservation(Long reservationId) {

        if (!reservationRepository.existsById(reservationId)) {
            throw new IllegalArgumentException("Reservation with id " + reservationId + " not found");
        }

        reservationRepository.deleteById(reservationId);

    }

}
