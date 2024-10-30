package hbs.hotel_booking_servise.domain.manager;


import hbs.hotel_booking_servise.error.EntityNotFoundEx;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//@AllArgsConstructor
@Slf4j
public class DefaultManager<T,  R extends JpaRepository<T, Long>> implements ManagerInterface<T> {

    private final R repository;

    public DefaultManager(R repository) {
        this.repository = repository;
    }

    public List<T> findAll() {
        log.debug("findAll() method is called " + repository.getClass() + "имя класса");
        System.out.println("Vizov findAll() method is called " + repository.getClass() + "имя класса");
        return repository.findAll();
    }




    public T findById(Long id) {
        log.debug("findById() method is called with id={}", id);

        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundEx("нет запрашиваемого объекта с ID " + id));
    }

    public T create(T object) {
        log.debug("create() method is called");

        return repository.save(object);
    }

    public T update(T object) {
        log.debug("update() method is called");

        return repository.save(object);
    }


    public void delete(Long id) {
        log.debug("delete() method is called with id={}", id);

        repository.delete(findById(id));
    }

    @Override
    public void deleteAll() {
        log.debug("deleteAll method is called");

        repository.deleteAll();
    }
    public long count(){
        return repository.count();
    }
}
