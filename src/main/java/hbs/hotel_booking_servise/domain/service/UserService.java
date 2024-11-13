package hbs.hotel_booking_servise.domain.service;

import hbs.hotel_booking_servise.domain.entity.User;

import hbs.hotel_booking_servise.domain.entity.UserRole;
import hbs.hotel_booking_servise.dto.UserDto;
import hbs.hotel_booking_servise.error.EntityNotFoundEx;
import hbs.hotel_booking_servise.error.IncorrectRequestEx;
import hbs.hotel_booking_servise.mapper.UserMapper;
import hbs.hotel_booking_servise.statistics.KafkaProducer;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import hbs.hotel_booking_servise.domain.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private final KafkaProducer kafkaProducer;
@Autowired
    private final PasswordEncoder passwordEncoder;

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


        boolean slotFree = repository.findByEmail(request.getEmail()).isEmpty() &&
                repository.findByName(request.getName()).isEmpty();

        if (slotFree) {
            User user= mapper.requestToEntity(request);
            user.setPassword(passwordEncoder.encode(user.getPassword()));

          if (request.getRole() == null || request.getRole().isEmpty()) {
              user.setRole(UserRole.USER);
          }
            UserDto.Response userResponse = mapper.entityToResponse
                    (repository.save
                            (user));


            kafkaProducer.sendToKafkaUserEvent(userResponse.getId());
            return userResponse;
        } else throw new IncorrectRequestEx("Name or email new user used");


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

    public User findByName(String name) throws EntityNotFoundEx {
        return repository.findByName(name).orElseThrow(() -> new EntityNotFoundEx("USER NOT FOUD"));
    }


}
