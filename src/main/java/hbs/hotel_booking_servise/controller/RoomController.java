package hbs.hotel_booking_servise.controller;

import hbs.hotel_booking_servise.domain.service.HotelService;
import hbs.hotel_booking_servise.domain.service.RoomService;
import hbs.hotel_booking_servise.dto.HotelDto;
import hbs.hotel_booking_servise.dto.RoomDto;

import hbs.hotel_booking_servise.specification.HotelFilter;
import hbs.hotel_booking_servise.specification.RoomFilter;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/room")
@AllArgsConstructor
@Slf4j
public class RoomController {
    @Autowired
    private final RoomService service;

    @GetMapping("/filter")
    public RoomDto.ListResponseCount filterBy(RoomFilter filter) {
        System.out.println("ROOM FILTER GET");
        return service.filterBy(filter);
    }

    @GetMapping
    public RoomDto.ListResponseCount getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public RoomDto.Response findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoomDto.Response create(@RequestBody RoomDto.Request request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public RoomDto.Response update(@PathVariable Long id, @RequestBody RoomDto.Request request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

