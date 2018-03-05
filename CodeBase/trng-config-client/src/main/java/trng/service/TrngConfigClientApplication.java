package trng.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RefreshScope
public class TrngConfigClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrngConfigClientApplication.class, args);
	}
	
	@Value("${server.port}")
	private String serverPort;
	
	@Value("${client.message}")
	private String message;
	
	@Value("${india.weather}")
	private String weather;
	
	@Value("${today.date}")
	private String todayDate;
	
	@Value("${datasource.name}")
	private String datasourceName;
	
	@Value("${database.connection.pool.size}")
	private String connectionPoolSize;
	
	@GetMapping("/properties")
	public String getProperties() {
		return 
				"<h4> serverPort: " + serverPort + ", "
				+ "message: " + message + ", "
				+ "weather: " + weather + ", "
				+ "todayDate: " + todayDate + ", "
				+ "datasourceName: " + datasourceName + ", "
				+ "connectionPoolSize: " + connectionPoolSize + "</h4>";
	}
}
