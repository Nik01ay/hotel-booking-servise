package hbs.hotel_booking_servise.domain.manager;

import hbs.hotel_booking_servise.domain.entity.Hotel;
import hbs.hotel_booking_servise.domain.repository.HotelRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class HotelManager extends DefaultManager<Hotel, HotelRepo> {


    public HotelManager(HotelRepo hotelRepo){
        super(hotelRepo);
    }




}
