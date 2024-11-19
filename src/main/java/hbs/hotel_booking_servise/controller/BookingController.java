package hbs.hotel_booking_servise.controller;

import hbs.hotel_booking_servise.domain.service.BookingService;
import hbs.hotel_booking_servise.dto.BookingDtoListResponseCount;
import hbs.hotel_booking_servise.dto.BookingDtoRequest;
import hbs.hotel_booking_servise.dto.BookingDtoResponse;

import lombok.AllArgsConstructor;

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
    public BookingDtoListResponseCount getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public BookingDtoResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookingDtoResponse create(@RequestBody BookingDtoRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public BookingDtoResponse update(@PathVariable Long id, @RequestBody BookingDtoRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
