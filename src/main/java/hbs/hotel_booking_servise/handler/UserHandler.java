package hbs.hotel_booking_servise.handler;

import hbs.hotel_booking_servise.adapter.UserMapper;
import hbs.hotel_booking_servise.domain.entity.User;
import hbs.hotel_booking_servise.domain.manager.UserManager;
import hbs.hotel_booking_servise.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserHandler extends DefaultHandlerImpl<User, UserDto.Response, UserDto.Request> {


    public UserHandler(UserMapper mapper, UserManager manager) {
        super(mapper, manager);
    }


}
