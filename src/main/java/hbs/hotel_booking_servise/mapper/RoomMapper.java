package hbs.hotel_booking_servise.mapper;

import hbs.hotel_booking_servise.domain.entity.Room;


import hbs.hotel_booking_servise.dto.RoomDtoListResponseCount;
import hbs.hotel_booking_servise.dto.RoomDtoRequest;
import hbs.hotel_booking_servise.dto.RoomDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoomMapper {


   @Mapping(source = "hotelId", target = "hotel.id")
    Room requestToEntity(RoomDtoRequest request);


    @Mapping(source = "hotel.id", target = "hotelId")
    RoomDtoResponse entityToResponse(Room room);

    List<RoomDtoResponse> entityListToListResponse(List<Room> entitys);


    default RoomDtoListResponseCount entityListToListResponseCount(List<Room> entityList, long count) {
        return new RoomDtoListResponseCount(entityListToListResponse(entityList), count);

    }

    ;
}
