package com.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.user.entity.User;
import com.user.repository.UserRepository;

@RestController
public class UserController 
{
	@Autowired
	UserRepository repo;
	
	// Register User  
	@PostMapping("/api/user/register")
	public ResponseEntity<User> registerUser(@RequestBody User user)
	{
		try 
		{
			User newUser = repo.save(user);
			return new ResponseEntity<>(newUser,HttpStatus.CREATED);
		}
		catch(Exception e)
		{
			 System.err.println("Error registering user: " + e.getMessage());
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
		
		
	}
	
	
	//fetch single user
	@GetMapping("/api/user/fetch/{id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public User getUser(@PathVariable Integer id)
	{
		User user = repo.findById(id).get();
		return user;
	
	}
	
	//fetch all user details
		@GetMapping("/api/user/fetch")
		public ResponseEntity<List<User>> getAllUser()
		{
			try
			{
		    List<User> users = repo.findAll();
		    return new ResponseEntity<>(users,HttpStatus.OK);
			}
			catch(Exception e)
			{
				System.err.println("Error fetching all users: " + e.getMessage());
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    
			
		}

}
