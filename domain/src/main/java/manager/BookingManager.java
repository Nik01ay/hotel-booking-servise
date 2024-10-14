package manager;

import entity.Booking;
import repository.BookingRepo;
import repository.UserRepo;

public class BookingManager extends DefaultManager<Booking, BookingRepo>{
    public BookingManager(BookingRepo bookingRepo){
        super(bookingRepo);
    }
}
