package com.app.api.users.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.app.api.users.model.CreateUserRequestModel;
import com.app.api.users.model.CreateUserResponseModel;
import com.app.api.users.model.GalleryEntity;

public interface UsersService {
	CreateUserResponseModel createUser(CreateUserRequestModel userReq);
	CreateUserResponseModel getUser(Integer userId);
	CreateUserResponseModel putUser(CreateUserRequestModel userReq, Integer userId);
	CreateUserResponseModel deleteUser(Integer userId);
	ArrayList<CreateUserResponseModel> getAllUsers();
	Boolean checkUserEmail(CreateUserRequestModel userReq, Integer userId);
	ResponseEntity<List<GalleryEntity>> getUserGalleries(Integer userId);
}
