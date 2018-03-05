package trng.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class TrngConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrngConfigServerApplication.class, args);
	}
}
