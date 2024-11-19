package hbs.hotel_booking_servise.domain.service;

import hbs.hotel_booking_servise.domain.entity.Hotel;

import hbs.hotel_booking_servise.domain.repository.HotelRepo;


import hbs.hotel_booking_servise.dto.HotelDtoListResponseCount;
import hbs.hotel_booking_servise.dto.HotelDtoRatingRequest;
import hbs.hotel_booking_servise.dto.HotelDtoRequest;
import hbs.hotel_booking_servise.dto.HotelDtoResponse;
import hbs.hotel_booking_servise.error.EntityNotFoundEx;
import hbs.hotel_booking_servise.mapper.HotelMapper;

import hbs.hotel_booking_servise.specification.HotelFilter;
import hbs.hotel_booking_servise.specification.HotelSpecification;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;



@Slf4j
@Service
@AllArgsConstructor
public class HotelService {
    @Autowired
    private final HotelRepo repository;

    @Autowired
    private final HotelMapper mapper;


    public HotelDtoListResponseCount filterBy(HotelFilter filter) {
        log.debug("filterBy() method is called");

        return mapper.entityListToListResponseCount(
                repository.findAll(
                                HotelSpecification.withFilter(filter), PageRequest
                                        .of(filter.getPageNumber(), filter.getPageSize()))
                        .getContent(), repository.count());
    }


    public HotelDtoListResponseCount findAll() {
        log.debug("findAll() method is called " + this.getClass() + "имя класса");

        return mapper.entityListToListResponseCount(repository.findAll(), repository.count());
    }


    public HotelDtoResponse findById(Long id) {
        log.debug("findById() method is called with id={}", id);

        return mapper.entityToResponse(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundEx("нет запрашиваемого объекта с ID " + id)));
    }

    public HotelDtoResponse create(HotelDtoRequest request) {
        log.debug("create() method is called");
        return mapper.entityToResponse(repository.save(mapper.requestToEntity(request)));
    }

    public HotelDtoResponse update(Long id, HotelDtoRequest object) {
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


    public HotelDtoResponse updateRating(Long id, HotelDtoRatingRequest request) throws EntityNotFoundEx {

        Hotel hotel = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundEx("нет запрашиваемого объекта с ID " + id));

        HotelDtoResponse h = calculateRating(hotel, request.getNewRating());
        hotel.setVotes(h.getVotes());
        hotel.setRating(h.getRating());
        repository.save(hotel);
        return findById(id);
    }

    private HotelDtoResponse calculateRating(Hotel hotel, Float newRatign) {

        Float rating = hotel.getRating();
        Integer votes = hotel.getVotes();

        if (rating != null && votes != null) {
            float totalRating = rating * votes;
            totalRating = totalRating - rating + newRatign;
            rating = totalRating / votes;
            rating = (float) Math.round(rating * 10);
            rating /= 10;
            votes++;

        } else {
            rating = newRatign;
            rating = (float) (Math.round(rating * 10) / 10);
            votes = 1;

        }

        HotelDtoResponse h = new HotelDtoResponse();
        h.setVotes(votes);
        h.setRating(rating);
        return h;
    }

}
