package hbs.hotel_booking_servise.mapper;

import hbs.hotel_booking_servise.domain.entity.Hotel;

import hbs.hotel_booking_servise.dto.HotelDtoListResponseCount;
import hbs.hotel_booking_servise.dto.HotelDtoRequest;
import hbs.hotel_booking_servise.dto.HotelDtoResponse;
import hbs.hotel_booking_servise.dto.HotelDtoResponseShort;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HotelMapper {


 // @Mapping(source = "id", target = "id")
    Hotel requestToEntity(HotelDtoRequest request);


    HotelDtoResponseShort entityListToListResponseShort(Hotel entity);

    HotelDtoResponse entityToResponse(Hotel entity);

    List<HotelDtoResponse> entityListToListResponse(List<Hotel> entitys);

   default HotelDtoListResponseCount entityListToListResponseCount(List<Hotel> entitys, Long count) {
        return new HotelDtoListResponseCount(entityListToListResponse(entitys), count);
    }



}

