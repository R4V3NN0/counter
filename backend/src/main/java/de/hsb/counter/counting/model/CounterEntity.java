package de.hsb.counter.counting.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@Getter
@Builder(toBuilder = true)
@Setter
@NoArgsConstructor
public class CounterEntity {
    @Id
    @GeneratedValue
    private long id;

    private int value;

    private LocalDateTime timeStamp;
}
