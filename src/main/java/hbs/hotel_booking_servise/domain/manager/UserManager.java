package hbs.hotel_booking_servise.domain.manager;

import hbs.hotel_booking_servise.domain.entity.User;

import hbs.hotel_booking_servise.domain.entity.UserRole;
import lombok.extern.slf4j.Slf4j;

import hbs.hotel_booking_servise.domain.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class UserManager extends DefaultManager<User, UserRepo>{

    public UserManager(UserRepo userRepo){
        super(userRepo);
    }


}
