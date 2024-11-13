package hbs.hotel_booking_servise.statistics;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticServise {

    private final EventDataNewUserRepository userRepository;
    private final EventDataNewBookingRepository bookingRepository;

    public void addBooking(EventDataNewBooking eventDataNewBooking) {
        bookingRepository.save(eventDataNewBooking);

    }

    public void addUser(EventDataNewUser eventDataNewUser) {
        userRepository.save(eventDataNewUser);

    }

    public List<EventDataNewBooking> getAllBooking() {
        return bookingRepository.findAll();
    }

    public List<EventDataNewUser> getAllUser() {
        return userRepository.findAll();
    }

    public Path generateCvsFile() {
        return createCsvFile(bookingRepository.findAll(), userRepository.findAll());
    }

    @SneakyThrows
    private Path createCsvFile(List<EventDataNewBooking> bookings, List<EventDataNewUser> users) {
        String fileName = "statistic.csv";
        Path filePath = Paths.get(fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            // Записываем заголовки
            writer.write("Booking UUID,Booking ID,User ID,Check In,Check Out");
            writer.newLine();

            for (EventDataNewBooking booking : bookings) {
                String line = String.format("%s,%d,%d,%s,%s",
                        booking.getUuid(),
                        booking.getId(),
                        booking.getUserId(),
                        booking.getCheckIn(),
                        booking.getCheckOut());
                writer.write(line);
                writer.newLine();
            }

            // Записываем пользователей (если требуется)
            writer.newLine(); // Пустая строка для разделения
            writer.write("User UUID,User ID");
            writer.newLine();

            for (EventDataNewUser user : users) {
                String line = String.format("%s,%d",
                        user.getUuid(),
                        user.getId());
                writer.write(line);
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return filePath;
    }

}
