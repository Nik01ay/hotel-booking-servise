package hbs.hotel_booking_servise.adapter;


import hbs.hotel_booking_servise.domain.entity.User;
import hbs.hotel_booking_servise.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends DefaultMapper<User, UserDto.Request, UserDto.Response> {



  //  @Mapping(source = "id", target = "id")
/*    @Override
    User requestToEntity(Long id, UserDto.Request request);


//@Override
    default List<UserDto.Response> entityListToListResponse(List<User> users) {
        return
                users.stream().map(
                        this::entityToResponse).toList();
    }*/
}