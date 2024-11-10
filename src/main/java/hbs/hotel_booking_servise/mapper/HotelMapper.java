package hbs.hotel_booking_servise.mapper;

import hbs.hotel_booking_servise.domain.entity.Hotel;
import hbs.hotel_booking_servise.domain.service.HotelService;
import hbs.hotel_booking_servise.dto.HotelDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HotelMapper {


  //@Mapping(source = "id", target = "id")
    Hotel requestToEntity(HotelDto.Request request);


    HotelDto.ResponseShort entityListToListResponseShort(Hotel entity);

    HotelDto.Response entityToResponse(Hotel entity);

    List<HotelDto.Response> entityListToListResponse(List<Hotel> entitys);

   default HotelDto.ListResponseCount entityListToListResponseCount(List<Hotel> entitys, Long count) {
        return new HotelDto.ListResponseCount(entityListToListResponse(entitys), count);
    }



}

