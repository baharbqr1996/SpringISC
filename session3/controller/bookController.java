package ir.freeland.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api")
public class bookController {
	
	public bookController() {
		super();
	}
	
	@RequestMapping(value = "/books")
	@ResponseBody
	public String GetBooks() {
		return "list of books";
	}
	
	@GetMapping("/book/{id}")
	@ResponseBody
	public String GetBookWithId(@PathVariable final long id) {
		return "get book with id=" + id;
	}

	@DeleteMapping("/book/{id}")
	@ResponseBody
	public String DeleteBookWithId(@PathVariable final long id) {
		return "delete book with id=" + id;
	}
	
	@PutMapping("/book/{id}")
	@ResponseBody
	public String putBookWithId(@PathVariable final long id) {
		return "update book with id=" + id;
	}
	
	@PostMapping("/book/create")
	@ResponseBody
	public String PostBook() {
		return "new book added.";
	}
	
	@GetMapping("/book/title/{name}")
	@ResponseBody
	public String GetBookTitle(@PathVariable final String name) {
		return "get book with id=" + name;
	}
	
	@GetMapping("/book/name={name}&publish={publish}")
	@ResponseBody
	public String GetBookTitle(@PathVariable final String name ,@PathVariable final String publish ) {
		return "get book with name=" + name +" and publish date=";
	}
	
}
