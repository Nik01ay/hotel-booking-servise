package manager;


import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@AllArgsConstructor
@Slf4j
public class DefaultManager<T,  R extends JpaRepository<T, Long>> {

    private final R repository;
    public List<T> findAll() {
        log.debug("findAllHotels() method is called");
        return repository.findAll();
    }




    public T findById(Long id) {
        log.debug("findById() method is called with id={}", id);

        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("В базе данных нет" + "  с ID " + id));
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


}
