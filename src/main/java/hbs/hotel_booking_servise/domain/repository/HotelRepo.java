package hbs.hotel_booking_servise.domain.repository;

import hbs.hotel_booking_servise.domain.entity.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface HotelRepo extends JpaRepository<Hotel, Long>, JpaSpecificationExecutor<Hotel> {

    @Override
    Page<Hotel> findAll(Specification<Hotel> spec, Pageable pageable);
    @Modifying
//booking_schema.
    @Query(value = "ALTER SEQUENCE hotels_id_seq RESTART WITH 1", nativeQuery = true)

    void resetIdCounter();
    @Modifying
   // @Transactional
    @Query(value = "TRUNCATE TABLE hotels CASCADE", nativeQuery = true)
    void truncateTable();
}
