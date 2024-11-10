package hbs.hotel_booking_servise.domain.service;

import java.util.List;

public interface ManagerInterface <T>{


    public List<T> findAll();

    public T findById(Long id);

    public T create(T object);

    public T update(T object);

    public void delete(Long id);

    public void deleteAll();
}
