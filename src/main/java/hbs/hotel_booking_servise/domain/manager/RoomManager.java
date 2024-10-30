package hbs.hotel_booking_servise.domain.manager;



import hbs.hotel_booking_servise.domain.entity.Room;
import hbs.hotel_booking_servise.domain.repository.RoomRepo;
import hbs.hotel_booking_servise.specification.HotelFilter;
import hbs.hotel_booking_servise.specification.HotelSpecification;
import hbs.hotel_booking_servise.specification.RoomFilter;
import hbs.hotel_booking_servise.specification.RoomSpecification;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoomManager extends  DefaultManager <Room, RoomRepo> {
    private final RoomRepo roomRepo;
    public RoomManager(RoomRepo repository) {
        super(repository);
        roomRepo = repository;
    }
        public List<Room> filterBy(RoomFilter filter) {


            return roomRepo.findAll(
                            RoomSpecification.withFilter(filter), PageRequest.of(filter.getPageNumber(), filter.getPageSize()))
                    .getContent();
        }




}
