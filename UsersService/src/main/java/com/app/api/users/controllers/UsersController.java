package com.app.api.users.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.app.api.users.model.CreateUserRequestModel;
import com.app.api.users.model.CreateUserResponseModel;
import com.app.api.users.model.GalleryEntity;
import com.app.api.users.service.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	UsersService usersService;
	
	Environment environment;
	
	RestTemplate restTemplate;
	
	Logger log = LoggerFactory.getLogger(UsersController.class);

	@GetMapping("/status/check")
	public String status()
	{
		return "Working on port " + env.getProperty("local.server.port") ;
	}
 
	@PostMapping(
			consumes = { MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE }
			)
	public ResponseEntity<Object> createUser(@RequestBody CreateUserRequestModel userDetails)
	{
		
		CreateUserResponseModel returnValue = usersService.createUser(userDetails);
		if(returnValue == null) {
			log.error("Bad request. There is already an user with same email-ID!!!!");
    		return new ResponseEntity<>("There is already an user with same email-ID ", new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
		else {
			log.info("user created successfully!!!!");
			return new ResponseEntity<>(returnValue, new HttpHeaders(), HttpStatus.CREATED);
		}
	}
	
    @GetMapping(value="/{userId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Object> getUser(@PathVariable("userId") String userId) {
       
    	CreateUserResponseModel returnValue = usersService.getUser(Integer.parseInt(userId));
    	if(returnValue == null) {
    		log.error("Request not found. There is no user with the ID: " + userId);
    		return new ResponseEntity<>("There is no user with the ID: "+userId, new HttpHeaders(), HttpStatus.NOT_FOUND);
    	}
    	else {
    		log.info("user CREATED successfully!!!!");
    		return new ResponseEntity<>(returnValue, new HttpHeaders(), HttpStatus.OK);
    	}
    }
    
    @PutMapping(value="/{userId}", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Object> putUser(@RequestBody CreateUserRequestModel userDetails, @PathVariable("userId") String userId) {
       
    	if(usersService.checkUserEmail(userDetails, Integer.parseInt(userId))) {
    		log.error("Bad Request. There is already an user with same email-ID");
    		return new ResponseEntity<>("There is already an user with same email-ID ", new HttpHeaders(), HttpStatus.BAD_REQUEST);
    	}
    		
    	CreateUserResponseModel returnValue = usersService.putUser(userDetails, Integer.parseInt(userId));
    	if(returnValue == null) {
    		log.error("Request not found. There is no user with the ID: " + userId);
    		return new ResponseEntity<>("There is no user with the ID: "+userId, new HttpHeaders(), HttpStatus.NOT_FOUND);
    	}
    	else {
    		log.info("user UPDATED successfully!!!!");
    		return new ResponseEntity<>(returnValue, new HttpHeaders(), HttpStatus.OK);
    	}
    }
    
    @DeleteMapping(value="/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Object> deleteUser(@PathVariable("userId") String userId) {
       
    	CreateUserResponseModel returnValue = usersService.deleteUser(Integer.parseInt(userId));
    	if(returnValue == null) {
    		log.error("Request not found. There is no user with the ID: " + userId);
    		return new ResponseEntity<>("There is no user with the ID: "+userId, new HttpHeaders(), HttpStatus.NOT_FOUND);
    	}
    	else {
    		log.info("user DELETED successfully!!!!");
    		return new ResponseEntity<>(returnValue, new HttpHeaders(), HttpStatus.OK);
    	}
    }
	
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Object> getAllUsers() {
       
        ArrayList<CreateUserResponseModel> allUsers = usersService.getAllUsers();
        
        log.info("ALL users returned successfully!!!!");
        return new ResponseEntity<>(allUsers, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping(value="/{userId}/galleries" ,produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Object> getUserGalleries(@PathVariable Integer userId) {
       
    	ResponseEntity<List<GalleryEntity>> albumsResponseEntity = usersService.getUserGalleries(userId);
    	if(albumsResponseEntity == null) {
   			log.error("Technical error, returned NULL response from Gallery service ");
       		return new ResponseEntity<>("Technical error, please try again later: ", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR); 
    		
    	} else if(albumsResponseEntity.getStatusCode().isSameCodeAs(HttpStatus.OK)) {
    		log.info("ALL user's galleries returned successfully!!!!");
            return new ResponseEntity<>(albumsResponseEntity.getBody(), new HttpHeaders(), HttpStatus.OK);
    	} else if(albumsResponseEntity.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND)) {
    		log.error("Request not found. There are no galleries for the user ID: " + userId);
    		return new ResponseEntity<>("There are no galleries for the user ID: "+userId, new HttpHeaders(), HttpStatus.NOT_FOUND);    		
    	} else {
    		log.error("Technical error, returned NULL response from Gallery service ");
       		return new ResponseEntity<>("Technical error, please try again later: ", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR); 
    	}
        	       	
     }        
	
}
