package hbs.hotel_booking_servise.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.util.Set;

@Entity(name = "hotels")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hotel {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private String name;

 private String announce;

 private String city;

 private String address;

 private Float distance;

 private Float rating = 0f;

 private Float rawRating = 0f;

 private Integer votes = 0;

 @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
 private Set<Room> rooms;

}
