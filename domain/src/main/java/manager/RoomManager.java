package manager;


import entity.Room;
import repository.RoomRepo;

public class RoomManager extends DefaultManager<Room, RoomRepo> {
    public RoomManager(RoomRepo roomRepo) {
        super(roomRepo);
    }
}
