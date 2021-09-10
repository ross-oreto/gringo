package io.oreto.gringo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping
    public ResponseEntity<String> displayWelcomeMessage() {
        return new ResponseEntity<>("Gringo!", HttpStatus.OK);
    }
}
