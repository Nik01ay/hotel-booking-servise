package hbs.hotel_booking_servise.controller;

import hbs.hotel_booking_servise.dto.HotelDto;
import hbs.hotel_booking_servise.dto.RoomDto;
import hbs.hotel_booking_servise.handler.DefaultHandler;
import hbs.hotel_booking_servise.handler.HotelHandler;
import hbs.hotel_booking_servise.handler.RoomHandler;
import hbs.hotel_booking_servise.specification.HotelFilter;
import hbs.hotel_booking_servise.specification.RoomFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/room")

@Slf4j
public class RoomController extends DefaultController<RoomDto.Response, RoomDto.Request> {
    private final RoomHandler roomHandler;
    public RoomController(RoomHandler handler) {
        super(handler);
        roomHandler = handler;
    }

    @GetMapping("/filter")
    public ResponseEntity<RoomDto.ListResponseCount> filterBy(RoomFilter filter) {
        System.out.println("ROOM FILTER GET");
        return ResponseEntity.ok(

                roomHandler.filterBy(filter))
                ;
    }
}
