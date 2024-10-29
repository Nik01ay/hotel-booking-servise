package hbs.hotel_booking_servise.controller;

import hbs.hotel_booking_servise.dto.HotelDto;
import hbs.hotel_booking_servise.handler.DefaultHandler;
import hbs.hotel_booking_servise.handler.HotelHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hotel")

@Slf4j
public class HotelController extends DefaultController<HotelDto.Response, HotelDto.Request> {
    public HotelController(HotelHandler handler) {
        super(handler);
    }
}
