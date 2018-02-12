package trng.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RibbonClient(name = "trng-ribbon-client")
@RequestMapping("/ribbon")
public class Resource {
	
	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
	
	@Autowired
	public RestTemplate restTemplate;
	
	@RequestMapping("/detail")
	public String getDetails(@RequestParam(value="name", defaultValue="John") String name){
		return getRole(name) + " " + getSalary(name);
	}

	
	public String getRole(String name){
		return restTemplate.getForObject("http://TRNG-ADMIN-SERVICE/admin/role?name=" + name, String.class);
	}
	
	public String getSalary(String name){
		return restTemplate.getForObject("http://TRNG-SALARY-SERVICE/payroll/salary?name=" + name, String.class);
	}
}
