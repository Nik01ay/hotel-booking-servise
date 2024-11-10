package hbs.hotel_booking_servise.mapper;


import hbs.hotel_booking_servise.domain.entity.Booking;
import hbs.hotel_booking_servise.domain.entity.Hotel;
import hbs.hotel_booking_servise.dto.BookingDto;

import hbs.hotel_booking_servise.dto.HotelDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookingMapper {

    @Mapping(source = "roomId", target = "room.id")
    Booking requestToEntity(BookingDto.Request request);


    @Mapping(source = "room.id", target = "roomId")
    BookingDto.Response entityToResponse(Booking entity);

    List<BookingDto.Response> entityListToListResponse(List<Booking> entitys);

    //@Override
    default BookingDto.ListResponseCount entityListToListResponseCount(List<Booking> entitys, Long count) {
        return new BookingDto.ListResponseCount (entityListToListResponse(entitys), count);

    }

}
