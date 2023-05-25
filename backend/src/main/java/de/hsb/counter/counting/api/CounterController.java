package de.hsb.counter.counting.api;

import de.hsb.counter.counting.model.CounterEntity;
import de.hsb.counter.counting.service.CounterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class CounterController {

    private final CounterService counterService;

    @GetMapping("/count")
    public CounterEntity getCount() {
        return counterService.getCount();
    }

    @GetMapping("/hi")
    public String string() {
        return "hi";
    }

    @PostMapping("/count")
    public void inc() {
        counterService.increase();
    }

    @PostMapping("/count/dec")
    public void dec() {
        counterService.decrease();
    }

    @DeleteMapping("/count")
    public void del() {
        counterService.reset();
    }
}
