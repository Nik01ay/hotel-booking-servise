package hbs.hotel_booking_servise.domain.manager;


import hbs.hotel_booking_servise.domain.entity.Room;
import hbs.hotel_booking_servise.domain.repository.RoomRepo;
import org.springframework.stereotype.Component;

@Component
public class RoomManager extends DefaultManager<Room, RoomRepo> {
    public RoomManager(RoomRepo roomRepo) {
        super(roomRepo);
    }
}
