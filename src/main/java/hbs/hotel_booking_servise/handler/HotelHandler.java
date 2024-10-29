package hbs.hotel_booking_servise.handler;


import hbs.hotel_booking_servise.adapter.HotelMapper;
import hbs.hotel_booking_servise.domain.entity.Hotel;
import hbs.hotel_booking_servise.domain.manager.HotelManager;
import hbs.hotel_booking_servise.dto.HotelDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class HotelHandler extends DefaultHandlerImpl<Hotel, HotelDto.Response, HotelDto.Request> {


    @Autowired
    private HotelManager manager;
    @Autowired
    private HotelMapper mapper;

    public HotelHandler(HotelMapper mapper, HotelManager manager) {
            super(mapper, manager);
        }


    }


