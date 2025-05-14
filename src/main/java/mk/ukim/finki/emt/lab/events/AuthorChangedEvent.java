package mk.ukim.finki.emt.lab.events;

import lombok.Getter;
import mk.ukim.finki.emt.lab.model.domain.Author;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;
import java.time.LocalDateTime;

@Getter
public class AuthorChangedEvent extends ApplicationEvent {
    private LocalDateTime dateTime;

    public AuthorChangedEvent(Author source) {
        super(source);
        this.dateTime=LocalDateTime.now();
    }

    public AuthorChangedEvent(Author source, Clock clock, LocalDateTime dateTime) {
        super(source, clock);
        this.dateTime = dateTime;
    }
}
