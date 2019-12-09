package hr.mladen.cikara.event.sourcing;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Tester {

  private Ship kr;
  private Cargo refact;
  private EventProcessor eProc;

  @BeforeEach
  void setup() {
    eProc = new EventProcessor();
    refact = new Cargo("Refactoring");
    kr = new Ship("King Roy");
  }

  @Test
  void arrivalSetsShipsLocation() {
    DomainEvent arrivalEvent = new ArrivalEvent(
            LocalDate.of(2005,11,1), Port.SAN_FRANCISCO, kr);
    eProc.process(arrivalEvent);

    Assertions.assertThat(kr.getPort()).isEqualByComparingTo(Port.SAN_FRANCISCO);
  }

  @Test
  void departurePutsShipsToSea() {
    eProc.process(new ArrivalEvent(LocalDate.of(2005,10,1), Port.LOS_ANGELES, kr));
    eProc.process(new ArrivalEvent(LocalDate.of(2005,11,1), Port.SAN_FRANCISCO, kr));
    eProc.process(new DepartureEvent(LocalDate.of(2005,11,1), Port.SAN_FRANCISCO, kr));

    Assertions.assertThat(kr.getPort()).isEqualByComparingTo(Port.AT_SEA);
  }

  @Test
  void visitingCanadaMarksCargo() {
    eProc.process(new LoadEvent(LocalDate.of(2005, 11, 1), refact, kr));
    eProc.process(new ArrivalEvent(LocalDate.of(2005,11,2), Port.VANCOUVER, kr));
    eProc.process(new DepartureEvent(LocalDate.of(2005,11,3), Port.VANCOUVER, kr));
    eProc.process(new ArrivalEvent(LocalDate.of(2005,11,4), Port.SAN_FRANCISCO, kr));
    eProc.process(new UnloadEvent(LocalDate.of(2005, 11, 5), refact, kr));

    Assertions.assertThat(refact.getHasBeenInCanada()).isTrue();
  }
}
