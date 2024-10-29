package hbs.hotel_booking_servise.handler;

import hbs.hotel_booking_servise.adapter.BookingMapper;
import hbs.hotel_booking_servise.domain.entity.Booking;
import hbs.hotel_booking_servise.domain.manager.BookingManager;
import hbs.hotel_booking_servise.dto.BookingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BookingHandler  extends DefaultHandlerImpl<Booking, BookingDto.Response, BookingDto.Request> {


    @Autowired
    private BookingManager manager;
    @Autowired
    private BookingMapper mapper;

    public BookingHandler(BookingMapper mapper, BookingManager manager) {
        super(mapper, manager);
    }
}
