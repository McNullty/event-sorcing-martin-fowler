package hr.mladen.cikara.event.sourcing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventSourcingApplication {

  public static void main(String[] args) {
    SpringApplication.run(EventSourcingApplication.class, args);
  }

}
