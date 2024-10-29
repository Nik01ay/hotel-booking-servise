package hbs.hotel_booking_servise.domain.repository;

import hbs.hotel_booking_servise.domain.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {
}
