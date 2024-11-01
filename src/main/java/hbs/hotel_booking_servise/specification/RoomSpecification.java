package hbs.hotel_booking_servise.specification;

import hbs.hotel_booking_servise.domain.entity.Booking;
import hbs.hotel_booking_servise.domain.entity.Room;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

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

    static Specification<Room> hasFreeRoomsWithDatesRange(Date dateStartRange, Date dateEndRange) {

            return (root, query, criteriaBuilder) -> {


                Predicate roomIsFree = criteriaBuilder.conjunction(); // Инициализируем пустое условие

                // Проверяем наличие диапазона дат
                if (dateStartRange != null && dateEndRange != null) {

                    Join<Booking, Room> roomBookings = root.join("room", JoinType.RIGHT);

                    // Условия для проверки свободных комнат
                    Predicate checkOutBeforeStart = criteriaBuilder.lessThan(roomBookings.get("checkOut"), dateStartRange);
                    Predicate checkInAfterEnd = criteriaBuilder.greaterThan(roomBookings.get("checkIn"), dateEndRange);

                    // Условие для проверки наличия бронирований
                    Predicate hasNoBookings = criteriaBuilder.isNull(roomBookings.get("id"));

                    // Объединяем условия: комната свободна, если нет бронирований или она свободна в заданном диапазоне
                    roomIsFree = criteriaBuilder.or(hasNoBookings,
                            criteriaBuilder.and(checkOutBeforeStart, checkInAfterEnd));
                }
                return roomIsFree;
            };

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

            return cb.equal(root.get("capacity"), capacity);
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
