package com.nagarro.flightSearch.dao;

import java.util.List;

import com.nagarro.flightSearch.model.User;

public interface UserDao
{
	public boolean saveUser(User user);
	
	public void updateUser(User user);

	public boolean deleteUser(User user);

	public User getById(int id);

	public User getByUsername(String username);

	public List<User> getUsers();

	public boolean isExists(User user);
}
