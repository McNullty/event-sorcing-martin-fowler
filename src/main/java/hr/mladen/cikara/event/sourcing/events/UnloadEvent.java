package hr.mladen.cikara.event.sourcing.events;

import hr.mladen.cikara.event.sourcing.Cargo;
import hr.mladen.cikara.event.sourcing.Ship;
import java.time.LocalDate;

import lombok.Getter;

@Getter
public class UnloadEvent extends DomainEvent {

  private final Cargo cargo;
  private final Ship ship;

  public UnloadEvent(final LocalDate occurred, final Cargo cargo, final Ship ship) {
    super(occurred);
    this.cargo = cargo;
    this.ship = ship;
  }

  @Override
  public void process() {
    ship.handleUnload(this);
  }
}
