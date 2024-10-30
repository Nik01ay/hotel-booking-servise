package hbs.hotel_booking_servise.error;

public class EntityNotFoundEx extends RuntimeException{

    public EntityNotFoundEx(String message) {
        super(message);
    }
}
