package hr.mladen.cikara.event.sourcing.events;

import hr.mladen.cikara.event.sourcing.Port;
import hr.mladen.cikara.event.sourcing.Ship;
import java.time.LocalDate;

import lombok.Getter;

@Getter
public class ArrivalEvent extends DomainEvent {

  private final Port port;
  private final Ship ship;

  public ArrivalEvent(final LocalDate occurred, final Port port, final Ship ship) {
    super(occurred);
    this.port = port;
    this.ship = ship;
  }

  @Override
  public void process() {
    ship.handleArrival(this);
  }
}
