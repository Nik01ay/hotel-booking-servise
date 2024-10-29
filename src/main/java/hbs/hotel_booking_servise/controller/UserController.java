package hbs.hotel_booking_servise.controller;

import hbs.hotel_booking_servise.dto.UserDto;
import hbs.hotel_booking_servise.handler.DefaultHandler;
import hbs.hotel_booking_servise.handler.UserHandler;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/user")
public class UserController extends DefaultController<UserDto.Response, UserDto.Request> {

    public UserController(UserHandler handler) {
        super(handler);

    }

    @Override
    public ResponseEntity<List<UserDto.Response>> getAll() {
        System.out.println("-----USER GET ALL CONTROLLER");
        return super.getAll();
    }
}