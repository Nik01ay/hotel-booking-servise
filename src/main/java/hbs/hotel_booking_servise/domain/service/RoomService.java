package hbs.hotel_booking_servise.domain.service;


import hbs.hotel_booking_servise.domain.entity.Room;
import hbs.hotel_booking_servise.domain.repository.RoomRepo;

import hbs.hotel_booking_servise.dto.RoomDtoListResponseCount;
import hbs.hotel_booking_servise.dto.RoomDtoRequest;
import hbs.hotel_booking_servise.dto.RoomDtoResponse;
import hbs.hotel_booking_servise.error.EntityNotFoundEx;
import hbs.hotel_booking_servise.mapper.RoomMapper;
import hbs.hotel_booking_servise.specification.RoomFilter;
import hbs.hotel_booking_servise.specification.RoomSpecification;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Slf4j
public class RoomService {
    @Autowired
    private final RoomRepo repository;
   @Autowired
   private final RoomMapper mapper;
        public RoomDtoListResponseCount filterBy(RoomFilter filter) {


            return mapper.entityListToListResponseCount(repository.findAll(
                            RoomSpecification.withFilter(filter), PageRequest.of(filter.getPageNumber(), filter.getPageSize()))
                    .getContent(), repository.count());


        }

    public RoomDtoListResponseCount findAll() {
        log.debug("findAll() method is called " + this.getClass() + "имя класса");

        return mapper.entityListToListResponseCount(repository.findAll(), repository.count());
    }


    public RoomDtoResponse findById(Long id) {
        log.debug("findById() method is called with id={}", id);

        return mapper.entityToResponse(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundEx("нет запрашиваемого объекта с ID " + id)));
    }

    public RoomDtoResponse create(RoomDtoRequest request) {
        log.debug("create() method is called");
        return mapper.entityToResponse(repository.save(mapper.requestToEntity(request)));
    }

    public RoomDtoResponse update(Long id, RoomDtoRequest object) {
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
