package com.app.api.users.repository;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.app.api.users.model.CreateUserResponseModel;

@Repository
public class UserRepository {
	
	static ArrayList<CreateUserResponseModel> usersDataPool;
	static {
		usersDataPool = new ArrayList<CreateUserResponseModel>();
		for (int i=0; i<5; i++) {
			CreateUserResponseModel inMemUserData = new CreateUserResponseModel();
			inMemUserData.setFirstName("FName-"+i);
			inMemUserData.setLastName("LName-"+i);
			inMemUserData.setEmail("email-"+i+"@email.com");
			inMemUserData.setUserId(i);	
			usersDataPool.add(inMemUserData);
		}
	}

	public static ArrayList<CreateUserResponseModel> userDataRepo() {
		
		
		return UserRepository.usersDataPool;
	}
	
}
