package hbs.hotel_booking_servise.controller;

import hbs.hotel_booking_servise.domain.service.BookingService;
import hbs.hotel_booking_servise.domain.service.HotelService;
import hbs.hotel_booking_servise.dto.BookingDto;

import hbs.hotel_booking_servise.dto.HotelDto;
import hbs.hotel_booking_servise.specification.HotelFilter;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/booking")
@AllArgsConstructor
@Slf4j
public class BookingController {
    @Autowired
    private BookingService service;



    @GetMapping
    public BookingDto.ListResponseCount getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public BookingDto.Response findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookingDto.Response create(@RequestBody BookingDto.Request request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public BookingDto.Response update(@PathVariable Long id, @RequestBody BookingDto.Request request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
