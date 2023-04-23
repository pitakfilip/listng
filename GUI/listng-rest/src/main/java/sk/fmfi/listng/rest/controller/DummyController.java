package sk.fmfi.listng.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kurzy")
public class DummyController {
    
    @GetMapping("/test")
    public String test(){
        return "JOJOJO JDEM TAM HAHAHAHAHAHA";
    }
}
