package hbs.hotel_booking_servise.domain.manager;

import hbs.hotel_booking_servise.domain.entity.Hotel;
import hbs.hotel_booking_servise.domain.repository.HotelRepo;
import hbs.hotel_booking_servise.specification.HotelFilter;
import hbs.hotel_booking_servise.specification.HotelSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;


@Slf4j
@Component
public class HotelManager extends DefaultManager<Hotel, HotelRepo> {
 private final HotelRepo hotelRepo;

    public HotelManager( HotelRepo repository) {
        super(repository);
        hotelRepo = repository;
    }


    public List<Hotel> filterBy(HotelFilter filter) {
        log.debug("filterBy() method is called");

        return hotelRepo.findAll(
                        HotelSpecification.withFilter(filter), PageRequest.of(filter.getPageNumber(), filter.getPageSize()))
                .getContent();
    }



}
