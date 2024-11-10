package hbs.hotel_booking_servise.controller;

import hbs.hotel_booking_servise.domain.service.UserService;
import hbs.hotel_booking_servise.dto.UserDto;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public List<UserDto.Response> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public UserDto.Response findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto.Response create(@RequestBody UserDto.Request request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public UserDto.Response update(@PathVariable Long id, @RequestBody UserDto.Request request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}



