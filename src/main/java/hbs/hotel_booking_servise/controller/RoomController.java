package hbs.hotel_booking_servise.controller;

import hbs.hotel_booking_servise.dto.RoomDto;
import hbs.hotel_booking_servise.handler.DefaultHandler;
import hbs.hotel_booking_servise.handler.RoomHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/room")

@Slf4j
public class RoomController extends DefaultController<RoomDto.Response, RoomDto.Request> {

    public RoomController(RoomHandler handler) {
        super(handler);
    }
}
