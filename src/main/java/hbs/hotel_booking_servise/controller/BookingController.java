package hbs.hotel_booking_servise.controller;

import hbs.hotel_booking_servise.dto.BookingDto;
import hbs.hotel_booking_servise.handler.BookingHandler;
import hbs.hotel_booking_servise.handler.DefaultHandler;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/booking")

@Slf4j
public class BookingController extends DefaultController<BookingDto.Response, BookingDto.Request> {


    public BookingController(BookingHandler handler) {
        super(handler);
    }
}