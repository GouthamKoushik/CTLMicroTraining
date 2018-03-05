package trng.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

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

		
	@FeignClient(name="TRNG-ADMIN-SERVICE", fallback = AdminClientFallback.class)
    interface AdminClient {
        @RequestMapping(value = "/admin/role", method = RequestMethod.GET)
        @HystrixCommand(fallbackMethod="trng.service.api.Resource.getDefaultRole")
        String getRole(@RequestParam("name") String name);
    }
	
	@FeignClient(name="TRNG-SALARY-SERVICE", fallback = SalaryClientFallback.class)
    interface SalaryClient {
        @RequestMapping(value = "/payroll/salary", method = RequestMethod.GET)
        @HystrixCommand(fallbackMethod="getDefaultSalary")
        String getSalary(@RequestParam("name") String name);
    }
	
	@Component
	static class AdminClientFallback implements AdminClient {

		@Override
		public String getRole(String name) {
			return "Sorry roles could not be found for " + name + ", please try again later!";
		}
	    
	}
	
	@Component
	static class SalaryClientFallback implements SalaryClient {

		@Override
		public String getSalary(String name) {
			return "Sorry salary could not be found for " + name + ", please try again later!";
		}
		
	}
}
