package trng.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign")
public class Resource {
	
	@Autowired
	AdminClient adminClient;
	
	@Autowired
	SalaryClient salaryClient;
	
	@RequestMapping("/detail")
	public String getDetails(@RequestParam(value="name", defaultValue="John") String name){
		return adminClient.getRole(name) + " " + salaryClient.getSalary(name);
	}

		
	@FeignClient("TRNG-ADMIN-SERVICE")
    interface AdminClient {
        @RequestMapping(value = "/admin/role", method = RequestMethod.GET)
        String getRole(@RequestParam("name") String name);
    }
	
	@FeignClient("TRNG-SALARY-SERVICE")
    interface SalaryClient {
        @RequestMapping(value = "/payroll/salary", method = RequestMethod.GET)
        String getSalary(@RequestParam("name") String name);
    }
}
