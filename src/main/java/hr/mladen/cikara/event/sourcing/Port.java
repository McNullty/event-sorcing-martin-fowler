package hr.mladen.cikara.event.sourcing;

public enum Port {
  AT_SEA(Country.NA), LOS_ANGELES(Country.USA), SAN_FRANCISCO(Country.USA), VANCOUVER(Country.CANADA);

  private Country country;

  Port(final Country country) {
    this.country = country;
  }

  public Country getCountry() {
    return this.country;
  }
}
