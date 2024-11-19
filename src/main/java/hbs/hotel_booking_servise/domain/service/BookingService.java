package hbs.hotel_booking_servise.domain.service;

import hbs.hotel_booking_servise.domain.entity.Booking;

import hbs.hotel_booking_servise.domain.repository.BookingRepo;

import hbs.hotel_booking_servise.dto.BookingDtoListResponseCount;
import hbs.hotel_booking_servise.dto.BookingDtoRequest;
import hbs.hotel_booking_servise.dto.BookingDtoResponse;

import hbs.hotel_booking_servise.error.EntityNotFoundEx;
import hbs.hotel_booking_servise.error.IncorrectRequestEx;
import hbs.hotel_booking_servise.mapper.BookingMapper;

import hbs.hotel_booking_servise.statistics.KafkaProducer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class BookingService {

    private final BookingRepo repository;


    private final BookingMapper mapper;

    private final KafkaProducer kafkaProducer;

  /*  public BookingDto.ListResponseCount filterBy(HotelFilter filter) {
        log.debug("filterBy() method is called");

        return mapper.entityListToListResponseCount(
                repository.findAll(
                                BookingSpecification.withFilter(filter), PageRequest
                                        .of(filter.getPageNumber(), filter.getPageSize()))
                        .getContent(), repository.count());
    }
*/

    public BookingDtoListResponseCount findAll() {
        log.debug("findAll() method is called " + this.getClass() + "имя класса");

        return mapper.entityListToListResponseCount(repository.findAll(), repository.count());
    }


    public BookingDtoResponse findById(Long id) {
        log.debug("findById() method is called with id={}", id);

        return mapper.entityToResponse(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundEx("нет запрашиваемого объекта с ID " + id)));
    }

    public BookingDtoResponse create(BookingDtoRequest request) throws IncorrectRequestEx {
        log.debug("create() method is called");
        System.out.println(request);
        System.out.println(mapper.requestToEntity(request));

        //todo проверка на свободные места

        List<Booking> useBooking = repository.findByRoomId(request.getRoomId());
        if
        (useBooking.stream().noneMatch(booking ->
                request.getCheckIn().isBefore(booking.getCheckOut()) &&
                        request.getCheckOut().isAfter(booking.getCheckIn()))) {

            BookingDtoResponse booking = mapper.entityToResponse
                    (repository.save
                            (mapper.requestToEntity(request)));

            kafkaProducer.sendToKafkaBookingEvent(booking);
            return booking;

        } else {
            throw new IncorrectRequestEx("Room is used");
        }
    }

    public BookingDtoResponse update(Long id, BookingDtoRequest object) {
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
