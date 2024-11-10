package hbs.hotel_booking_servise.mapper;

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
public interface RoomMapper  {



 //  @Mapping(source = "id", target = "id")
   // @Mapping(source = "request.hotelId", target = "hotel.id")
   // Room requestToEntity( RoomDto.Request request);


  @Mapping(source = "hotelId", target = "hotel.id")
    Room requestToEntity(RoomDto.Request request);


 @Mapping(source = "hotel.id", target = "hotelId")
    RoomDto.Response entityToResponse(Room room);

    List<RoomDto.Response> entityListToListResponse(List<Room> entitys);


    default RoomDto.ListResponseCount entityListToListResponseCount(List<Room> entityList, long count) {
        return new RoomDto.ListResponseCount(entityListToListResponse(entityList), count);

    }

    ;
}
