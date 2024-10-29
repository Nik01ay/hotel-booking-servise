package hbs.hotel_booking_servise.domain.repository;

import hbs.hotel_booking_servise.domain.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepo extends JpaRepository<Hotel, Long> {
}
