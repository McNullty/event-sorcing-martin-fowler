package hr.mladen.cikara.event.sourcing;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class LoadEvent extends DomainEvent {
  private final Cargo cargo;
  private final Ship ship;

  public LoadEvent(final LocalDate occurred, final Cargo cargo, final Ship ship) {
    super(occurred);
    this.cargo = cargo;
    this.ship = ship;
  }

  @Override
  public void process() {
    ship.handleLoad(this);
  }
}
