package de.hsb.counter.counting.service;

import de.hsb.counter.counting.model.CounterEntity;
import de.hsb.counter.counting.repository.CounterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CounterService {

    private final CounterRepository counterRepository;

    public CounterEntity getCount() {
        Optional<CounterEntity> counterEntity = counterRepository.findFirstByOrderByTimeStampDesc();
        return counterEntity.orElseGet(() -> CounterEntity.builder()
                .value(0)
                .build());

        }

    public void increase() {
        CounterEntity currentValue = getCount();
        CounterEntity newValue = currentValue.toBuilder()
                .timeStamp(LocalDateTime.now())
                .value(currentValue.getValue()+1)
                .build();
        counterRepository.save(newValue);
    }

    public void reset() {
        CounterEntity currentValue = getCount();
        CounterEntity newValue = currentValue.toBuilder()
//                .timeStamp(LocalDateTime.now())
                .value(currentValue.getValue()-1)
                .build();
        counterRepository.save(newValue);
    }
}
