package hbs.hotel_booking_servise.controller;

import hbs.hotel_booking_servise.domain.service.UserService;



import hbs.hotel_booking_servise.dto.UserDtoRequest;
import hbs.hotel_booking_servise.dto.UserDtoResponse;
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
    public List<UserDtoResponse> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public UserDtoResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDtoResponse create(@RequestBody UserDtoRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public UserDtoResponse update(@PathVariable Long id, @RequestBody UserDtoRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}



