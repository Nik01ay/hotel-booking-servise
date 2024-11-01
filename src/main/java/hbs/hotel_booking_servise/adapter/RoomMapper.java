package hbs.hotel_booking_servise.adapter;

import hbs.hotel_booking_servise.domain.entity.Hotel;
import hbs.hotel_booking_servise.domain.entity.Room;
import hbs.hotel_booking_servise.dto.HotelDto;
import hbs.hotel_booking_servise.dto.RoomDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoomMapper extends DefaultMapper<Room, RoomDto.Request, RoomDto.Response> {

    @Override
   @Mapping(source = "id", target = "id")
    @Mapping(source = "request.hotelId", target = "hotel.id")
    Room requestToEntity(Long id, RoomDto.Request request);

    @Override
   @Mapping(source = "request.hotelId", target = "hotel.id")
    Room requestToEntity(RoomDto.Request request);

    @Override
   @Mapping(source = "hotel.id", target = "hotelId")
    RoomDto.Response entityToResponse(Room room);


    default RoomDto.ListResponseCount entityListToListResponseCount(List<Room> entityList, long count) {
        return new RoomDto.ListResponseCount(entityListToListResponse(entityList), count);

    }

    ;
}
