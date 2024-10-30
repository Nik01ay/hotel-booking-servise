package hbs.hotel_booking_servise.handler;

import hbs.hotel_booking_servise.error.EntityNotFoundEx;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DefaultHandler <RS, RQ>{

    List<RS> findAll();
    RS findById(Long id) throws EntityNotFoundEx;

    RS update(Long id, RQ request);

    RS create(RQ request);

    void delete(Long id);

    void deleteAll();




}
