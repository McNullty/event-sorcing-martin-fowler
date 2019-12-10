package hr.mladen.cikara.event.sourcing;

import hr.mladen.cikara.event.sourcing.events.ArrivalEvent;
import hr.mladen.cikara.event.sourcing.events.DepartureEvent;
import hr.mladen.cikara.event.sourcing.events.DomainEvent;
import hr.mladen.cikara.event.sourcing.events.LoadEvent;
import hr.mladen.cikara.event.sourcing.events.UnloadEvent;
import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Tester {

  private Ship kingRoy;
  private Cargo books;
  private EventProcessor eventProcessor;

  @BeforeEach
  void setup() {
    eventProcessor = new EventProcessor();
    books = new Cargo("Refactoring");
    kingRoy = new Ship("King Roy");
  }

  @Test
  void arrivalSetsShipsLocation() {
    DomainEvent arrivalEvent = new ArrivalEvent(
            LocalDate.of(2005,11,1), Port.SAN_FRANCISCO, kingRoy);
    eventProcessor.process(arrivalEvent);

    Assertions.assertThat(kingRoy.getPort()).isEqualByComparingTo(Port.SAN_FRANCISCO);
  }

  @Test
  void departurePutsShipsToSea() {
    eventProcessor.process(new ArrivalEvent(LocalDate.of(2005,10,1), Port.LOS_ANGELES, kingRoy));
    eventProcessor.process(new ArrivalEvent(LocalDate.of(2005,11,1), Port.SAN_FRANCISCO, kingRoy));
    eventProcessor.process(new DepartureEvent(LocalDate.of(2005,11,1), Port.SAN_FRANCISCO, kingRoy));

    Assertions.assertThat(kingRoy.getPort()).isEqualByComparingTo(Port.AT_SEA);
  }

  @Test
  void visitingCanadaMarksCargo() {
    eventProcessor.process(new LoadEvent(LocalDate.of(2005, 11, 1), books, kingRoy));
    eventProcessor.process(new ArrivalEvent(LocalDate.of(2005,11,2), Port.VANCOUVER, kingRoy));
    eventProcessor.process(new DepartureEvent(LocalDate.of(2005,11,3), Port.VANCOUVER, kingRoy));
    eventProcessor.process(new ArrivalEvent(LocalDate.of(2005,11,4), Port.SAN_FRANCISCO, kingRoy));
    eventProcessor.process(new UnloadEvent(LocalDate.of(2005, 11, 5), books, kingRoy));

    Assertions.assertThat(books.getHasBeenInCanada()).isTrue();
  }
}
