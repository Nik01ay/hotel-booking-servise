package hbs.hotel_booking_servise.statistics;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventDataNewUserRepository extends MongoRepository<EventDataNewUser, Long> {
}