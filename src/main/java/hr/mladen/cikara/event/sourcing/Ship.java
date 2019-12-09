package hr.mladen.cikara.event.sourcing;

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

  void handleDeparture(final DepartureEvent departureEvent) {
    port = Port.AT_SEA;
  }

  void handleArrival(final ArrivalEvent arrivalEvent) {
    port = arrivalEvent.getPort();

    cargo.forEach(c -> c.handleArrival(arrivalEvent));
  }

  void handleLoad(final LoadEvent loadEvent) {
    cargo.add(loadEvent.getCargo());
  }

  void handleUnload(final UnloadEvent unloadEvent) {
    cargo.remove(unloadEvent.getCargo());
  }
}
