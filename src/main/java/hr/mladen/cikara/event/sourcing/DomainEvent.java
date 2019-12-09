package hr.mladen.cikara.event.sourcing;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public abstract class DomainEvent {

  private final LocalDate recorder;
  private final LocalDate occurred;

  DomainEvent(final LocalDate occurred) {
    this.occurred = occurred;
    this.recorder = LocalDate.now();
  }

  public abstract void process();
}
