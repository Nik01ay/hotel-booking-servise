package hbs.hotel_booking_servise.adapter;

import hbs.hotel_booking_servise.domain.entity.Room;
import hbs.hotel_booking_servise.dto.RoomDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoomMapper  extends DefaultMapper<Room, RoomDto.Request, RoomDto.Response> {

   @Mapping(source = "id", target = "id")
    @Override
    Room requestToEntity(Long id,RoomDto.Request request);



    default List<RoomDto.Response> entityListToListResponse(List<Room> rooms) {
        return
                rooms.stream().map(
                        this::entityToResponse).toList();
    }
}
