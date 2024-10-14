package manager;

import entity.Hotel;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import repository.HotelRepo;

import java.util.List;


@Slf4j
public class HotelManager extends DefaultManager<Hotel, HotelRepo> {


    public HotelManager(HotelRepo hotelRepo){
        super(hotelRepo);
    }




}
