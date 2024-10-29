package hbs.hotel_booking_servise.handler;


import hbs.hotel_booking_servise.adapter.RoomMapper;
import hbs.hotel_booking_servise.domain.entity.Room;
import hbs.hotel_booking_servise.domain.manager.RoomManager;
import hbs.hotel_booking_servise.dto.RoomDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoomHandler extends DefaultHandlerImpl<Room, RoomDto.Response, RoomDto.Request> {




    public RoomHandler(RoomMapper mapper, RoomManager manager) {
        super(mapper, manager);
    }


}
