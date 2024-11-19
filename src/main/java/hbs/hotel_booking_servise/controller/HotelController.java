package hbs.hotel_booking_servise.controller;

import hbs.hotel_booking_servise.domain.service.HotelService;


import hbs.hotel_booking_servise.dto.HotelDtoListResponseCount;
import hbs.hotel_booking_servise.dto.HotelDtoRatingRequest;
import hbs.hotel_booking_servise.dto.HotelDtoRequest;
import hbs.hotel_booking_servise.dto.HotelDtoResponse;
import hbs.hotel_booking_servise.specification.HotelFilter;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/hotel")
@AllArgsConstructor
@Slf4j
public class HotelController {

    @Autowired
    private HotelService service;

    @GetMapping("/filter")
    public HotelDtoListResponseCount filterBy(HotelFilter filter) {
        log.info("HotelController: filterBy() method is called");
        return service.filterBy(filter)
                ;
    }


    @GetMapping
    public HotelDtoListResponseCount getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public HotelDtoResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HotelDtoResponse create(@RequestBody HotelDtoRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public HotelDtoResponse update(@PathVariable Long id, @RequestBody HotelDtoRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }


    @PutMapping("/{id}/rating/")
    public HotelDtoResponse updateRating(@PathVariable Long id,
                                         @RequestBody @Valid HotelDtoRatingRequest request) {
        return service.updateRating(id, request);
    }
}

