package io.oreto.gringo;

import io.oreto.gringo.jackson5.Jackson5Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AppController {

    @GetMapping
    public Jackson5Response displayWelcomeMessage() {
        Map<String, String> data = new HashMap<String, String>(){{ put("welcome", "Gringo!"); }};
        return new Jackson5Response(data);
    }
}
