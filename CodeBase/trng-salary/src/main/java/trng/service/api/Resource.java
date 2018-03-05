package trng.service.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payroll")
public class Resource {
	
	@RequestMapping("/salary")
    public String getSalary(@RequestParam(value="name", defaultValue="John") String name) {
        if(name == null || name.isEmpty()){
        	return "0.0";
        }
        
        return name + "'s salary is 50,000 INR";
    }

}
