package hr.mladen.cikara.event.sourcing;

import hr.mladen.cikara.event.sourcing.events.DomainEvent;
import java.util.ArrayList;
import java.util.List;

public class EventProcessor {
  private final List<DomainEvent> log;

  public EventProcessor() {
    log = new ArrayList<>();
  }

  public void process(DomainEvent domainEvent) {
    domainEvent.process();
    log.add(domainEvent);
  }
}
