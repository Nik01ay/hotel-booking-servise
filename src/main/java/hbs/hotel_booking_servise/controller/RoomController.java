package hbs.hotel_booking_servise.controller;

import hbs.hotel_booking_servise.domain.service.RoomService;


import hbs.hotel_booking_servise.dto.RoomDtoListResponseCount;
import hbs.hotel_booking_servise.dto.RoomDtoRequest;
import hbs.hotel_booking_servise.dto.RoomDtoResponse;

import hbs.hotel_booking_servise.specification.RoomFilter;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/room")
@AllArgsConstructor
@Slf4j
public class RoomController {
    @Autowired
    private final RoomService service;

    @GetMapping("/filter")
    public RoomDtoListResponseCount filterBy(RoomFilter filter) {
        System.out.println("ROOM FILTER GET");
        return service.filterBy(filter);
    }

    @GetMapping
    public RoomDtoListResponseCount getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public RoomDtoResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoomDtoResponse create(@RequestBody RoomDtoRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public RoomDtoResponse update(@PathVariable Long id, @RequestBody RoomDtoRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

