package hbs.hotel_booking_servise.error;

public class IncorrectRequestEx extends RuntimeException{

    public IncorrectRequestEx(String message) {
        super(message);
    }
}
