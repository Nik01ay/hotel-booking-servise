package hbs.hotel_booking_servise;

import hbs.hotel_booking_servise.domain.repository.BookingRepo;
import hbs.hotel_booking_servise.domain.repository.HotelRepo;
import hbs.hotel_booking_servise.domain.repository.RoomRepo;
import hbs.hotel_booking_servise.domain.repository.UserRepo;
import hbs.hotel_booking_servise.domain.service.BookingService;
import hbs.hotel_booking_servise.domain.service.HotelService;
import hbs.hotel_booking_servise.domain.service.RoomService;
import hbs.hotel_booking_servise.domain.service.UserService;

import hbs.hotel_booking_servise.mapper.HotelMapper;
import hbs.hotel_booking_servise.mapper.HotelMapperImpl;
import hbs.hotel_booking_servise.statistics.KafkaMListener;
import hbs.hotel_booking_servise.statistics.KafkaProducer;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.event.annotation.AfterTestExecution;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
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
@DirtiesContext
//@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
public class AbstractTest {

    @MockBean
    private KafkaProducer kafkaProducer;

    @MockBean
    private KafkaMListener kafkaMListener;

    @InjectMocks
    private UserService userService;

    protected static   PostgreSQLContainer postgreSQLContainer;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    protected  MockMvc mockMvc;


    @DynamicPropertySource
    public static void registerProperties(DynamicPropertyRegistry registry) {
        String jdbcUrl = postgreSQLContainer.getJdbcUrl();

        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.url",()->jdbcUrl);

    }
    @BeforeAll
    static void startContainer(){
        DockerImageName postgres = DockerImageName.parse("postgres:12.3");



        postgreSQLContainer = new PostgreSQLContainer<>(postgres)
                .withInitScript("initTest.sql"); // Загрузка скрипта инициализации
        postgreSQLContainer.start();
    }


    @AfterAll
    static void stopContainer(){
        postgreSQLContainer.stop();
    }

}
