package hbs.hotel_booking_servise.domain.service;

import hbs.hotel_booking_servise.domain.entity.User;

import hbs.hotel_booking_servise.dto.UserDto;
import hbs.hotel_booking_servise.error.EntityNotFoundEx;
import hbs.hotel_booking_servise.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import hbs.hotel_booking_servise.domain.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private final UserRepo repository;

    @Autowired
    private final UserMapper mapper;

    public List<UserDto.Response> findAll() {
        log.debug("findAll() method is called " + repository.getClass() + "имя класса");
        System.out.println("Vizov findAll() method is called " + repository.getClass() + "имя класса");
        return mapper.entityListToListResponse(repository.findAll());
    }


    public UserDto.Response findById(Long id) {
        log.debug("findById() method is called with id={}", id);

        return mapper.entityToResponse(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundEx("нет запрашиваемого объекта с ID " + id)));
    }

    public UserDto.Response create(UserDto.Request request) {
        log.debug("create() method is called");
        return mapper.entityToResponse(repository.save(mapper.requestToEntity(request)));
    }

    public UserDto.Response update(Long id, UserDto.Request object) {
        log.debug("update() method is called");

        User user = mapper.requestToEntity(object);
        repository.findById(id)
                .map(u -> {
                    user.setId(u.getId());
                    repository.save(user);
                    return u;
                });


        return mapper.entityToResponse(user);


    }


    public void delete(Long id) {
        log.debug("delete() method is called with id={}", id);
        repository.findById(id)
                .map(u -> {
                    repository.delete(u);
                    return u;
                });
    }


    public void deleteAll() {
        log.debug("deleteAll method is called");

        repository.deleteAll();
    }

    public long count() {
        return repository.count();
    }


}
