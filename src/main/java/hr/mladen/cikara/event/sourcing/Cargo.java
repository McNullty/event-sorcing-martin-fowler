package hr.mladen.cikara.event.sourcing;

import lombok.Getter;

@Getter
public class Cargo {
  private final String description;
  private Boolean hasBeenInCanada = Boolean.FALSE;

  public Cargo(final String description) {
    this.description = description;
  }

  void handleArrival(final ArrivalEvent arrivalEvent) {
    if( arrivalEvent.getPort().getCountry() == Country.CANADA ) {
      hasBeenInCanada = Boolean.TRUE;
    }
  }
}
