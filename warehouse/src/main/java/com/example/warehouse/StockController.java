package com.example.warehouse;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/stock")
public class StockController {

    private final Map<String, Long> qtys;

    public StockController() {
        this.qtys = new HashMap<>();
    }

    @GetMapping("{cakeId}")
    public Long getQuantity(@PathVariable String cakeId) {
        return qtys.computeIfAbsent(cakeId, s -> ThreadLocalRandom.current().nextLong(0, 10));
    }
}
