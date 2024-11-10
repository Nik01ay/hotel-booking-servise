package hbs.hotel_booking_servise.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import java.time.Instant;
import java.time.LocalDate;

@Entity(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id")
   // @ToString.Exclude
    private Room room;

    @ManyToOne
    @JoinColumn(name = "user_id")
   // @ToString.Exclude
    private User user;

    private LocalDate checkIn;

    private LocalDate checkOut;
}
