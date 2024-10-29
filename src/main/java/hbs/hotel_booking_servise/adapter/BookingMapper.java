package hbs.hotel_booking_servise.adapter;


import hbs.hotel_booking_servise.domain.entity.Booking;
import hbs.hotel_booking_servise.dto.BookingDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookingMapper extends DefaultMapper<Booking, BookingDto.Request, BookingDto.Response> {

    @Mapping(source = "id", target = "id")
    @Override
    Booking requestToEntity(Long id, BookingDto.Request request);


    //@Override
    default List<BookingDto.Response> entityListToListResponse(List<Booking> bookings) {
        return
                bookings.stream().map(
                        this::entityToResponse).toList();
    }

}
