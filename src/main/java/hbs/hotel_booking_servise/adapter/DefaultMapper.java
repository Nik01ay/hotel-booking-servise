package hbs.hotel_booking_servise.adapter;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


import java.util.List;
// todo Будет лил работать?
//@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DefaultMapper <E, RQ, RS>{
    E requestToEntity (RQ request);
    E requestToEntity (Long id, RQ request);
    RS entityToResponse(E entity);
   List<RS> entityListToListResponse(List<E> entityList);
}
