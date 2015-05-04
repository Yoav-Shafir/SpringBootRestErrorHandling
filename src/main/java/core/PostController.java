package core;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostController {
	
	@ExceptionHandler({PostNotFoundException.class})
	public ResponseEntity<CustomError> postNotFound(PostNotFoundException e) {
		long id = e.getPostId();
		CustomError error = new CustomError(4, "post [" + id + "] not found");
		return new ResponseEntity<CustomError>(error, HttpStatus.NOT_FOUND);
	}
	
	// curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET 127.0.0.1:8080/post/0
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Post postById(@PathVariable long id) {
		// some condition...
		if (id == 0) 
			throw new PostNotFoundException(id);
		return new Post(1, "title", "description");
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////
	
	// curl -i -H "Accept: application/json" -H "Content-Type: application/json" 
	// 	-X POST -d '{"id":1,"title":"very long title","desc":"some description"}' 127.0.0.1:8080/post
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Post> savePost(@Valid @RequestBody Post post) {	
		// saving item...
		// set location header.
		HttpHeaders headers = new HttpHeaders();
		long madeUpId = 1;
		URI locationUri = URI.create("http://localhost:8080/item/" + madeUpId);
		headers.setLocation(locationUri);
		
		// create ResponseEntity.
		ResponseEntity<Post> responseEntity = new ResponseEntity<Post>(post, headers, HttpStatus.CREATED);
				return responseEntity;
	}
	
	
	
}
