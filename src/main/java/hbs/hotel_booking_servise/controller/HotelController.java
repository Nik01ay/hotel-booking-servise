package hbs.hotel_booking_servise.controller;

import hbs.hotel_booking_servise.domain.service.HotelService;
import hbs.hotel_booking_servise.domain.service.UserService;
import hbs.hotel_booking_servise.dto.HotelDto;

import hbs.hotel_booking_servise.specification.HotelFilter;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hotel")
@AllArgsConstructor
@Slf4j
public class HotelController {

    @Autowired
    private HotelService service;

    @GetMapping("/filter")
    public HotelDto.ListResponseCount filterBy(HotelFilter filter) {
        log.info("HotelController: filterBy() method is called");
        return service.filterBy(filter)
                ;
    }


    @GetMapping
    public HotelDto.ListResponseCount getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public HotelDto.Response findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HotelDto.Response create(@RequestBody HotelDto.Request request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public HotelDto.Response update(@PathVariable Long id, @RequestBody HotelDto.Request request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

