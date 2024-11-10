package hbs.hotel_booking_servise.domain.service;

import hbs.hotel_booking_servise.domain.entity.Hotel;
import hbs.hotel_booking_servise.domain.entity.User;
import hbs.hotel_booking_servise.domain.repository.HotelRepo;
import hbs.hotel_booking_servise.domain.repository.UserRepo;
import hbs.hotel_booking_servise.dto.HotelDto;
import hbs.hotel_booking_servise.dto.UserDto;
import hbs.hotel_booking_servise.error.EntityNotFoundEx;
import hbs.hotel_booking_servise.mapper.HotelMapper;
import hbs.hotel_booking_servise.mapper.UserMapper;
import hbs.hotel_booking_servise.specification.HotelFilter;
import hbs.hotel_booking_servise.specification.HotelSpecification;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@AllArgsConstructor
public class HotelService {
    @Autowired
    private final HotelRepo repository;

    @Autowired
    private final HotelMapper mapper;


    public HotelDto.ListResponseCount filterBy(HotelFilter filter) {
        log.debug("filterBy() method is called");

        return mapper.entityListToListResponseCount(
                repository.findAll(
                                HotelSpecification.withFilter(filter), PageRequest
                                        .of(filter.getPageNumber(), filter.getPageSize()))
                        .getContent(), repository.count());
    }


    public HotelDto.ListResponseCount findAll() {
        log.debug("findAll() method is called " + this.getClass() + "имя класса");

        return mapper.entityListToListResponseCount(repository.findAll(), repository.count());
    }


    public HotelDto.Response findById(Long id) {
        log.debug("findById() method is called with id={}", id);

        return mapper.entityToResponse(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundEx("нет запрашиваемого объекта с ID " + id)));
    }

    public HotelDto.Response create(HotelDto.Request request) {
        log.debug("create() method is called");
        return mapper.entityToResponse(repository.save(mapper.requestToEntity(request)));
    }

    public HotelDto.Response update(Long id, HotelDto.Request object) {
        log.debug("update() method is called");


        Hotel hotel = mapper.requestToEntity(object);
        repository.findById(id)
                .map(h -> {
                    hotel.setId(h.getId());
                    repository.save(hotel);
                    return h;
                });

        return mapper.entityToResponse(hotel);


    }


    public void delete(Long id) {
        log.debug("delete() method is called with id={}", id);
        repository.findById(id)
                .map(h -> {
                    repository.delete(h);
                    return h;
                });
    }


    public void deleteAll() {
        log.debug("deleteAll method is called");

        repository.deleteAll();
    }




}
