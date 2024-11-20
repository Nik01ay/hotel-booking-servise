package hbs.hotel_booking_servise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication//(scanBasePackages = "hbs.hotel_booking_servise")
public class HotelBookingServiseApplication {

	public static void main(String[] args) {

	ApplicationContext applicationContext = SpringApplication.run(HotelBookingServiseApplication.class, args);

	String[] beanNames = applicationContext.getBeanDefinitionNames();
            for (String beanName : beanNames) {
		System.out.println(beanName);
	}
            System.out.println("Количество бинов: " + applicationContext.getBeanDefinitionCount());
}


}
