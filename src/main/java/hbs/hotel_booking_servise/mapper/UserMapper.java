package hbs.hotel_booking_servise.mapper;


import hbs.hotel_booking_servise.domain.entity.User;

import hbs.hotel_booking_servise.dto.UserDtoRequest;
import hbs.hotel_booking_servise.dto.UserDtoResponse;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;



@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {



  //@Mapping(source = "id", target = "id")

    User requestToEntity(UserDtoRequest request);






    UserDtoResponse entityToResponse(User user);

    default List<UserDtoResponse> entityListToListResponse(List<User> users) {

       return users.stream().map(
                this::entityToResponse)
                        .toList();

    }

}