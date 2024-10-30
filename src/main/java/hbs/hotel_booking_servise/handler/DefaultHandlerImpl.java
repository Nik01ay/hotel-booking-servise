package hbs.hotel_booking_servise.handler;

import hbs.hotel_booking_servise.adapter.DefaultMapper;
import hbs.hotel_booking_servise.domain.manager.ManagerInterface;
import hbs.hotel_booking_servise.error.EntityNotFoundEx;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;


import java.util.List;



@Slf4j

public class DefaultHandlerImpl<E, RS, RQ> implements DefaultHandler<RS, RQ>{

    private final DefaultMapper<E, RQ, RS> mapper;
    private final ManagerInterface<E> manager;

    public DefaultHandlerImpl(DefaultMapper<E, RQ, RS> mapper, ManagerInterface<E> manager) {
        this.mapper = mapper;
        this.manager = manager;
    }

    @Override
    public List<RS> findAll() {
        log.info("handler.getAll() method is called " + manager.getClass() + " +++ "+ mapper.getClass());
        return mapper.entityListToListResponse(manager.findAll());
    }

    @Override
    public RS findById(Long id) throws EntityNotFoundEx {
        return mapper.entityToResponse(manager.findById(id));
    }

    @Override
    public RS update(Long id, RQ request) {
        return mapper.entityToResponse(manager.update(
                mapper.requestToEntity(request)
        ));
    }

    @Override
    public RS create(RQ request) {
        return mapper.entityToResponse(manager.create(mapper.requestToEntity(request)));
    }

    @Override
    public void delete(Long id) {
        manager.delete(id);
    }

    @Override
    public void deleteAll() {
        manager.deleteAll();
    }
}
