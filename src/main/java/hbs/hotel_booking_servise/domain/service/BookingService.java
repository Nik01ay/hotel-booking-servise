package hbs.hotel_booking_servise.domain.service;

import hbs.hotel_booking_servise.domain.entity.Booking;
import hbs.hotel_booking_servise.domain.entity.Hotel;
import hbs.hotel_booking_servise.domain.repository.BookingRepo;
import hbs.hotel_booking_servise.dto.BookingDto;
import hbs.hotel_booking_servise.dto.HotelDto;
import hbs.hotel_booking_servise.error.EntityNotFoundEx;
import hbs.hotel_booking_servise.error.IncorrectRequestEx;
import hbs.hotel_booking_servise.mapper.BookingMapper;
import hbs.hotel_booking_servise.specification.HotelFilter;
import hbs.hotel_booking_servise.specification.HotelSpecification;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class BookingService {
    @Autowired
    private final BookingRepo repository;

    @Autowired
    private final BookingMapper mapper;


  /*  public BookingDto.ListResponseCount filterBy(HotelFilter filter) {
        log.debug("filterBy() method is called");

        return mapper.entityListToListResponseCount(
                repository.findAll(
                                BookingSpecification.withFilter(filter), PageRequest
                                        .of(filter.getPageNumber(), filter.getPageSize()))
                        .getContent(), repository.count());
    }
*/

    public BookingDto.ListResponseCount findAll() {
        log.debug("findAll() method is called " + this.getClass() + "имя класса");

        return mapper.entityListToListResponseCount(repository.findAll(), repository.count());
    }


    public BookingDto.Response findById(Long id) {
        log.debug("findById() method is called with id={}", id);

        return mapper.entityToResponse(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundEx("нет запрашиваемого объекта с ID " + id)));
    }

    public BookingDto.Response create(BookingDto.Request request) throws IncorrectRequestEx {
        log.debug("create() method is called");
        System.out.println(request);
        System.out.println(mapper.requestToEntity(request));

        //todo проверка на свободные места

        List<Booking> useBooking = repository.findByRoomId(request.getRoomId());
        if
        (useBooking.stream().noneMatch(booking ->
                request.getCheckIn().isBefore(booking.getCheckOut()) &&
                        request.getCheckOut().isAfter(booking.getCheckIn())))
        {
            return mapper.entityToResponse(repository.save(mapper.requestToEntity(request)));
        } else {
            throw new IncorrectRequestEx("Room is used");
        }
    }

    public BookingDto.Response update(Long id, BookingDto.Request object) {
        log.debug("update() method is called");


        Booking booking = mapper.requestToEntity(object);
        repository.findById(id)
                .map(b -> {
                    booking.setId(b.getId());
                    repository.save(booking);
                    return b;
                });

        return mapper.entityToResponse(booking);


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
