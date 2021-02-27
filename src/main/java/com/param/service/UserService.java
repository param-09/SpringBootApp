 package com.param.service;



import java.util.List;	

import org.springframework.data.domain.Page;

import com.param.model.User;
	
public interface UserService 
{

	List<User> getAllUsers();
	
	void saveUser(User user);
	
	User getUserById(long id); 

	void deleteUserById(long id);

	Page<User> findPaginated(int pageNo, int pageSize);

	List<User> findByKeyword(String keyword);

	

} 
