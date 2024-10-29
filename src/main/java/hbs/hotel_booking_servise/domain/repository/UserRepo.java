package hbs.hotel_booking_servise.domain.repository;

import hbs.hotel_booking_servise.domain.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepo extends JpaRepository<User, Long>{
    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);
}
