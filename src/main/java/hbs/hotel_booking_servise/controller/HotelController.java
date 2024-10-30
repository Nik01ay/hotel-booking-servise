package hbs.hotel_booking_servise.controller;

import hbs.hotel_booking_servise.dto.HotelDto;
import hbs.hotel_booking_servise.handler.DefaultHandler;
import hbs.hotel_booking_servise.handler.HotelHandler;
import hbs.hotel_booking_servise.specification.HotelFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hotel")

@Slf4j
public class HotelController extends DefaultController<HotelDto.Response, HotelDto.Request> {
    private final HotelHandler hotelHandler;

    public HotelController(HotelHandler hotelHandler) {
        super(hotelHandler);
        this.hotelHandler = hotelHandler;
    }

    @GetMapping("/filter")
    public ResponseEntity<HotelDto.ListResponseCount> filterBy(HotelFilter filter) {
        log.info("HotelController: filterBy() method is called");

        return ResponseEntity.ok(
                hotelHandler.filterBy(filter))
                ;
    }
}