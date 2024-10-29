package hbs.hotel_booking_servise.adapter;



import java.util.List;
// todo Будет лил работать?
public interface DefaultMapper <E, RQ, RS>{
    E requestToEntity (RQ request);
    E requestToEntity (Long id, RQ request);
    RS entityToResponse(E entity);
   List<RS> entityListToListResponse(List<E> entityList);
}
