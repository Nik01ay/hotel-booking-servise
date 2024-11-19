package hbs.hotel_booking_servise.specification;

import hbs.hotel_booking_servise.domain.entity.Booking;
import hbs.hotel_booking_servise.domain.entity.Room;


import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.time.*;
import java.util.List;
import java.util.Set;

public interface RoomSpecification {


    static Specification<Room> withFilter(RoomFilter filter) {
        return Specification
                .where(byRoomId(filter.getRoomId()))
                .and(byName(filter.getName()))
                .and(byMinCost(filter.getMinCost()))
                .and(byMaxCost(filter.getMaxCost()))
                .and(byCapacity(filter.getCapacity()))
                .and(byHotelId(filter.getHotelId()))
                .and(hasFreeRoomsWithDatesRange(filter.getDateStartRange(), filter.getDateEndRange()))

                ;
    }

    static Specification<Room> hasFreeRoomsWithDatesRange(LocalDate dateStartRange, LocalDate dateEndRange) {

        return (root, query, criteriaBuilder) -> {
            Predicate roomIsFree = criteriaBuilder.conjunction();
            if (dateStartRange != null && dateEndRange != null) {
                System.out.println("PROVERKA");
                Join<Room, Booking> roomBooking = root.join("bookings", JoinType.LEFT);

                Predicate checkOutBeforeStart = criteriaBuilder.lessThanOrEqualTo(roomBooking.get("checkOut"), dateStartRange);
                Predicate checkInAfterEnd = criteriaBuilder.greaterThanOrEqualTo(roomBooking.get("checkIn"), dateEndRange);
                // todo разобраться с логикой

                Predicate hasNoBookings = criteriaBuilder.isNull(roomBooking.get("id"));

                roomIsFree = criteriaBuilder
                        .or(
                                hasNoBookings
                                ,
                                criteriaBuilder
                                        .or(checkOutBeforeStart, checkInAfterEnd));

            }
            return roomIsFree;
        }
                ;

    }




        static Specification<Room> byRoomId(Long roomId) {
        return ((root, query, cb) -> {
            if (roomId == null) {
                return null;
            }

            return cb.equal(root.get("id"), roomId);
        });
    }


    static Specification<Room> byName(String name) {
        return ((root, query, cb) -> {
            if (name == null) {
                return null;
            }

            return cb.equal(root.get("name"), name);
        });
    }

    static Specification<Room> byMinCost(Integer minCost) {
        return ((root, query, cb) -> {
            if (minCost == null) {
                return null;
            }

            return cb.greaterThanOrEqualTo(root.get("price"), minCost);
        });
    }

    static Specification<Room> byMaxCost(Integer maxCost) {
        return ((root, query, cb) -> {
            if (maxCost == null) {
                return null;
            }

            return cb.lessThanOrEqualTo(root.get("price"), maxCost);
        });
    }

    static Specification<Room> byCapacity(Integer capacity) {
        return ((root, query, cb) -> {
            if (capacity == null) {
                return null;
            }
            // номера с вместимостью больше чем в фильтре
            return cb.greaterThan(root.get("capacity"), capacity);
        });
    }

    static Specification<Room> byHotelId(Long hotelId) {
        return ((root, query, cb) -> {
            if (hotelId == null) {
                return null;
            }
            root.fetch("hotel"); // Загружаем связанную сущность Hotel
            return cb.equal(root.get("hotel").get("id"), hotelId);

        });
    }
}
