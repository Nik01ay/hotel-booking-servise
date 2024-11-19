package hbs.hotel_booking_servise.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserDtoRequest {

    private String name;
    private String password;
    private String email;
    private String role;

}

