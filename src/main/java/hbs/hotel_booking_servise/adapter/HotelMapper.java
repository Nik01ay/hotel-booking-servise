package hbs.hotel_booking_servise.adapter;

import hbs.hotel_booking_servise.domain.entity.Hotel;
import hbs.hotel_booking_servise.domain.manager.HotelManager;
import hbs.hotel_booking_servise.dto.HotelDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;


@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {HotelManager.class})
public interface HotelMapper  extends  DefaultMapper<Hotel, HotelDto.Request, HotelDto.Response>{

    /*Hotel hotelRequestToHotel(HotelDto.Request request);

    //@Mapping(source = "id", target = "id")
    Hotel hotelRequestToHotel(Long id, HotelDto.Request request);


    HotelDto.ResponseShort hotelToHotelResponseShort(Hotel hotel);

    HotelDto.Response hotelToHotelResponse(Hotel hotel);

   // List<HotelDto.Response> entitysToHotelResponses(List<Hotel> hotels);
*/
  /*  default HotelDto.ResponseList entityListToListResponse(List<Hotel> hotels) {
        return new HotelDto.ResponseList(new ArrayList<>());
    }

*/
}

