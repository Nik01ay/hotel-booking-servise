package hbs.hotel_booking_servise;

import hbs.hotel_booking_servise.domain.service.BookingService;
import hbs.hotel_booking_servise.domain.service.HotelService;
import hbs.hotel_booking_servise.domain.service.RoomService;
import hbs.hotel_booking_servise.domain.service.UserService;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
@Testcontainers
public class AbstractTest {
    protected  static PostgreSQLContainer postgreSQLContainer;



    static {
        DockerImageName postgres = DockerImageName.parse("postgres:12.3");

        postgreSQLContainer = (PostgreSQLContainer) new PostgreSQLContainer(postgres)
                .withReuse(true);
        postgreSQLContainer.start();

    }

    @DynamicPropertySource
    public static void registerProperties(DynamicPropertyRegistry registry) {
        String jdbcUrl = postgreSQLContainer.getJdbcUrl();

        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.url",()->jdbcUrl);

    }

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected UserService userService;


    @Autowired
    protected HotelService hotelService;

    @Autowired
    protected RoomService roomService;

    @Autowired
    protected BookingService bookingService;



}
