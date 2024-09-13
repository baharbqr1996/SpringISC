package myPackage;
import java.util.Arrays;
import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class UserController  {
	
	     private final RestTemplate restTemplate;

    // Constructor injection of RestTemplate
    public UserController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
    	 try {
        String url = "https://jsonplaceholder.typicode.com/users";
        User[] users = restTemplate.getForObject(url, User[].class);
        for (User user : users) {
            //System.out.println("ID: " + user.getId());
            System.out.println("Name: " + user.getName());
            System.out.println("Username: " + user.getUsername());
           // System.out.println("Email: " + user.getEmail());
            System.out.println("-------------------");
        }
        return ResponseEntity.ok(Arrays.asList(users));
    	 }catch (RestClientException e) {
    	        // لاگ کردن یا مدیریت خطا
    	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching users");
    	    }
}
}