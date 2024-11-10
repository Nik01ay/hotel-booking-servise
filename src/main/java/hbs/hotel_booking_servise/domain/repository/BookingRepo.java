package hbs.hotel_booking_servise.domain.repository;

import hbs.hotel_booking_servise.domain.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long> {
    List<Booking> findByRoomId(Long RoomId);
}
