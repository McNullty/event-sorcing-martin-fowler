package hr.mladen.cikara.event.sourcing;

import java.time.LocalDate;

import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class DepartureEvent extends DomainEvent {

  private final Port port;
  private final Ship ship;

  public DepartureEvent(final LocalDate occurred, final Port port, final Ship ship) {
    super(occurred);
    this.port = port;
    this.ship = ship;
  }

  @Override
  public void process() {
    ship.handleDeparture(this);
  }
}
