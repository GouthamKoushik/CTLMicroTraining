package trng.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/controller")
public class HelloResource {
	
	@GetMapping("/hello")
	public String getMessage(){
		return "Hello from Spring Boot!";
	}

}
