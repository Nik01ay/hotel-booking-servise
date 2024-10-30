package hbs.hotel_booking_servise.controller;

import hbs.hotel_booking_servise.error.EntityNotFoundEx;
import hbs.hotel_booking_servise.handler.DefaultHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
//@RestController
//@RequestMapping("/api/v1/{entityName}")
public class DefaultController<RS, RQ> {


    private final DefaultHandler<RS, RQ> handler;

    public DefaultController(DefaultHandler<RS, RQ> handler) {
        this.handler = handler;
    }
    @GetMapping
    public ResponseEntity<List<RS>> getAll() {
        log.info("controller. getAll() method is called " + handler.getClass());
        return ResponseEntity.ok(handler.findAll() );
    }
    @GetMapping("/{id}")
    public ResponseEntity<RS> findById(@PathVariable Long id) {
        log.info ("findById() method is called with id={}", id);

        return ResponseEntity.ok(
                handler.findById(id));
    }
    @PostMapping
    public ResponseEntity<RS>  create(@RequestBody RQ request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(handler.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RS> update(@PathVariable Long id, @RequestBody RQ request) throws EntityNotFoundEx {
        log.info("update() method is called with id={}", id);

        return ResponseEntity.ok(
                handler.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("delete() method is called with id={}", id);
        handler.delete(id);
        return ResponseEntity.noContent().build();
    }
}
