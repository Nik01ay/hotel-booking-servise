package hbs.hotel_booking_servise.handler;


import hbs.hotel_booking_servise.adapter.HotelMapper;
import hbs.hotel_booking_servise.adapter.RoomMapper;
import hbs.hotel_booking_servise.domain.entity.Room;
import hbs.hotel_booking_servise.domain.manager.HotelManager;
import hbs.hotel_booking_servise.domain.manager.RoomManager;
import hbs.hotel_booking_servise.dto.HotelDto;
import hbs.hotel_booking_servise.dto.RoomDto;
import hbs.hotel_booking_servise.specification.HotelFilter;
import hbs.hotel_booking_servise.specification.RoomFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoomHandler extends DefaultHandlerImpl<Room, RoomDto.Response, RoomDto.Request> {


    @Autowired
    private RoomManager manager;
    @Autowired
    private RoomMapper mapper;

    public RoomHandler(RoomMapper mapper, RoomManager manager) {
        super(mapper, manager);

    }

    public RoomDto.ListResponseCount filterBy(RoomFilter filter) {
        return mapper.entityListToListResponseCount(manager.filterBy(filter), manager.count());

    }




}
