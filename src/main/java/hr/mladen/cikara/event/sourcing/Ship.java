package hr.mladen.cikara.event.sourcing;

import hr.mladen.cikara.event.sourcing.events.ArrivalEvent;
import hr.mladen.cikara.event.sourcing.events.DepartureEvent;
import hr.mladen.cikara.event.sourcing.events.LoadEvent;
import hr.mladen.cikara.event.sourcing.events.UnloadEvent;
import java.util.ArrayList;

import lombok.Getter;

@Getter
public class Ship {

  private final String name;
  private Port port;
  private final ArrayList<Cargo> cargo;

  public Ship(final String name) {
    this.name = name;
    this.cargo = new ArrayList<>();
  }

  public void handleDeparture(final DepartureEvent departureEvent) {
    port = Port.AT_SEA;
  }

  public void handleArrival(final ArrivalEvent arrivalEvent) {
    port = arrivalEvent.getPort();

    cargo.forEach(c -> c.handleArrival(arrivalEvent));
  }

  public void handleLoad(final LoadEvent loadEvent) {
    cargo.add(loadEvent.getCargo());
  }

  public void handleUnload(final UnloadEvent unloadEvent) {
    cargo.remove(unloadEvent.getCargo());
  }
}
