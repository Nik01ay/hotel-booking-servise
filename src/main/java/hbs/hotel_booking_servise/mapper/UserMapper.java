package hbs.hotel_booking_servise.mapper;


import hbs.hotel_booking_servise.domain.entity.User;
import hbs.hotel_booking_servise.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {



  // @Mapping(source = "id", target = "id")

    User requestToEntity(UserDto.Request request);



   /* @Mapping(source = "id", target = "id")
    User RequestToEntity(Long id, UserDto.Request request);


    */
    UserDto.Response entityToResponse(User user);

    default List<UserDto.Response> entityListToListResponse(List<User> users) {

       return users.stream().map(
                this::entityToResponse)
                        .toList();

    }
/*@Override
    default List<UserDto.Response> entityListToListResponse(List<User> users) {
        return
                users.stream().map(
                        this::entityToResponse).toList();
    }*/
}