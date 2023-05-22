package de.hsb.counter.counting.api;

import de.hsb.counter.counting.model.CounterEntity;
import de.hsb.counter.counting.service.CounterService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CounterControllerTest {

    @Mock
    private CounterService counterService;

    @InjectMocks
    private CounterController counterController;


    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, -1, 1000})
    void returnsSameValueAsService(int expected) {
        CounterEntity wrapped = CounterEntity.builder()
                .value(expected)
                .build();

        when(counterService.getCount()).thenReturn(wrapped);
        CounterEntity result = counterController.getCount();

        assertEquals(expected, result.getValue());
    }
}