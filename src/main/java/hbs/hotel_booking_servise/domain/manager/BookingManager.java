package hbs.hotel_booking_servise.domain.manager;

import hbs.hotel_booking_servise.domain.entity.Booking;
import hbs.hotel_booking_servise.domain.repository.BookingRepo;
import org.springframework.stereotype.Component;

@Component
public class BookingManager extends DefaultManager<Booking, BookingRepo>{

    public BookingManager(BookingRepo bookingRepo){
        super(bookingRepo);
    }
}
