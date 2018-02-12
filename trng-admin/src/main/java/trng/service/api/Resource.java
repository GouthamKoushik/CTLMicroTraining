package trng.service.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class Resource {
	
	
	@RequestMapping("/role")
    public String getRole(@RequestParam(value="name", defaultValue="John") String name) {
        if(name == null || name.isEmpty()){
        	return "UNKNOWN";
        }
        
        return name + "'s role is >> ADMIN";
    }

}
