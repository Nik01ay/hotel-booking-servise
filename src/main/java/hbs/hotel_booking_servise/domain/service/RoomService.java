package hbs.hotel_booking_servise.domain.service;



import hbs.hotel_booking_servise.domain.entity.Booking;
import hbs.hotel_booking_servise.domain.entity.Room;
import hbs.hotel_booking_servise.domain.repository.RoomRepo;
import hbs.hotel_booking_servise.dto.BookingDto;
import hbs.hotel_booking_servise.dto.RoomDto;
import hbs.hotel_booking_servise.error.EntityNotFoundEx;
import hbs.hotel_booking_servise.mapper.RoomMapper;
import hbs.hotel_booking_servise.specification.RoomFilter;
import hbs.hotel_booking_servise.specification.RoomSpecification;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class RoomService {
    @Autowired
    private final RoomRepo repository;
   @Autowired
   private final RoomMapper mapper;
        public RoomDto.ListResponseCount filterBy(RoomFilter filter) {


            return mapper.entityListToListResponseCount(repository.findAll(
                            RoomSpecification.withFilter(filter), PageRequest.of(filter.getPageNumber(), filter.getPageSize()))
                    .getContent(), repository.count());


        }

    public RoomDto.ListResponseCount findAll() {
        log.debug("findAll() method is called " + this.getClass() + "имя класса");

        return mapper.entityListToListResponseCount(repository.findAll(), repository.count());
    }


    public RoomDto.Response findById(Long id) {
        log.debug("findById() method is called with id={}", id);

        return mapper.entityToResponse(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundEx("нет запрашиваемого объекта с ID " + id)));
    }

    public RoomDto.Response create(RoomDto.Request request) {
        log.debug("create() method is called");
        return mapper.entityToResponse(repository.save(mapper.requestToEntity(request)));
    }

    public RoomDto.Response update(Long id, RoomDto.Request object) {
        log.debug("update() method is called");


        Room room = mapper.requestToEntity(object);
        repository.findById(id)
                .map(r -> {
                    room.setId(r.getId());
                    repository.save(room);
                    return r;
                });

        return mapper.entityToResponse(room);


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
