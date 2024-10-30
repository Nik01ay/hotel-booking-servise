package hbs.hotel_booking_servise.handler;


import hbs.hotel_booking_servise.adapter.HotelMapper;
import hbs.hotel_booking_servise.domain.entity.Hotel;
import hbs.hotel_booking_servise.domain.manager.HotelManager;
import hbs.hotel_booking_servise.dto.HotelDto;
import hbs.hotel_booking_servise.specification.HotelFilter;
import hbs.hotel_booking_servise.specification.HotelSpecification;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class HotelHandler extends DefaultHandlerImpl<Hotel, HotelDto.Response, HotelDto.Request> {


    @Autowired
    private HotelManager manager;
    @Autowired
    private HotelMapper mapper;

    public HotelHandler(HotelMapper mapper, HotelManager manager) {
            super(mapper, manager);
        }

    public HotelDto.ListResponseCount filterBy(HotelFilter filter) {
        return mapper.entityListToListResponseCount(manager.filterBy(filter), manager.count());
    }

    }


