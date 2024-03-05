package com.app.api.users.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.api.users.model.CreateUserRequestModel;
import com.app.api.users.model.CreateUserResponseModel;
import com.app.api.users.model.GalleryEntity;
import com.app.api.users.repository.UserRepository;

@Service
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment environment;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Override
	public CreateUserResponseModel createUser(CreateUserRequestModel userReq) {
		// TODO Auto-generated method stub
		ArrayList<CreateUserResponseModel> usersDataPool = UserRepository.userDataRepo();
		
		//userDetails.setUserId(UUID.randomUUID().toString());
		for(CreateUserResponseModel user : usersDataPool) {
			if(user.getEmail().equalsIgnoreCase(userReq.getEmail())) {
				return null;
			}
		}
		
		CreateUserResponseModel newUser = new CreateUserResponseModel();
		newUser.setFirstName(userReq.getFirstName());
		newUser.setLastName(userReq.getLastName());
		newUser.setEmail(userReq.getEmail());
		List<Integer> userIdList = usersDataPool.stream().map(CreateUserResponseModel::getUserId).collect(Collectors.toList());
		newUser.setUserId(Collections.max(userIdList) + 1);
		usersDataPool.add(newUser);
		return newUser;
	}

	@Override
	public CreateUserResponseModel getUser(Integer userId) {
		ArrayList<CreateUserResponseModel> usersDataPool = UserRepository.userDataRepo();
		
		for(CreateUserResponseModel user : usersDataPool) {
			if(user.getUserId().equals(userId)) {
				return user;
			}
		}
		return null;
	}

	@Override
	public CreateUserResponseModel putUser(CreateUserRequestModel userReq, Integer userId) { 
		ArrayList<CreateUserResponseModel> usersDataPool = UserRepository.userDataRepo();
		
		for(CreateUserResponseModel user : usersDataPool) {
			if(user.getUserId().equals(userId)) {
				user.setFirstName(userReq.getFirstName());
				user.setLastName(userReq.getLastName());
				user.setEmail(userReq.getEmail());
				return user;
			}
		}
		
		
		return null;
	}

	@Override
	public CreateUserResponseModel deleteUser(Integer userId) {
		
		ArrayList<CreateUserResponseModel> usersDataPool = UserRepository.userDataRepo();
		
		for(CreateUserResponseModel user : usersDataPool) {
			if(user.getUserId().equals(userId)) {
				usersDataPool.remove(user);
				return user;
			}
		}
				
		return null;
	}
	
	@Override
	public ArrayList<CreateUserResponseModel> getAllUsers() {
		ArrayList<CreateUserResponseModel> usersDataPool = UserRepository.userDataRepo();
				
		return usersDataPool;
		
	}
	
	@Override
	public Boolean checkUserEmail(CreateUserRequestModel userReq, Integer userId) {
		ArrayList<CreateUserResponseModel> usersDataPool = UserRepository.userDataRepo();
		for(CreateUserResponseModel user : usersDataPool) {
			if(user.getEmail().equalsIgnoreCase(userReq.getEmail()) && user.getUserId().equals(userId)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public ResponseEntity<List<GalleryEntity>> getUserGalleries(Integer userId) {
		String albumsUrl = String.format(environment.getProperty("galleries.endpoint.host"), userId);
        
    	ResponseEntity<List<GalleryEntity>> albumsResponseEntity = restTemplate.exchange(albumsUrl+userId, HttpMethod.GET, null, new ParameterizedTypeReference<List<GalleryEntity>>() {
        });
        return albumsResponseEntity;
    	
	}
	

}
