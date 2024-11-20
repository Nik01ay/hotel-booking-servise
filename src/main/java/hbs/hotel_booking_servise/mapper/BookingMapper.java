package hbs.hotel_booking_servise.mapper;


import hbs.hotel_booking_servise.domain.entity.Booking;


import hbs.hotel_booking_servise.dto.BookingDtoListResponseCount;
import hbs.hotel_booking_servise.dto.BookingDtoRequest;
import hbs.hotel_booking_servise.dto.BookingDtoResponse;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookingMapper {

   @Mapping(source = "roomId", target = "room.id")
    Booking requestToEntity(BookingDtoRequest request);


   @Mapping(source = "room.id", target = "roomId")
   BookingDtoResponse entityToResponse(Booking entity);

    List<BookingDtoResponse> entityListToListResponse(List<Booking> entitys);

    default BookingDtoListResponseCount entityListToListResponseCount(List<Booking> entitys, Long count) {
        return new BookingDtoListResponseCount (entityListToListResponse(entitys), count);

    }

}
