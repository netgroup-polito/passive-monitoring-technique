package it.polito.tesiclustering.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import it.polito.tesiclustering.dao.UserDao;
import it.polito.tesiclustering.model.Graph;
import it.polito.tesiclustering.model.User;
import it.polito.tesiclustering.model.exception.DaoException;
import it.polito.tesiclustering.model.exception.UserAlreadyCreatedException;

public class UserService {

	private List<User> users = new LinkedList<User>();
	// dao used to save objects in DB
	private UserDao userDao = new UserDao();
	int id = 0;

	public UserService(List<User> users) {
		super();
		this.users = users;
	}

	public UserService() {
	}

	public User getUser(Integer id) throws DaoException {

		try {
			return this.userDao.getUser(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DaoException("Cannot get User.", e);
		}
	}

	public List<User> getUsers() throws DaoException {
		try {

			return this.userDao.getAllUser();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DaoException("Cannot get Users.", e);
		}
	}

	public User createUser(User user) throws UserAlreadyCreatedException, DaoException {

		if (user == null)
			return user;

		User createdUser = null;

		// set createdBy
		user.setCreatedBy("System");
		// set createdDate to now
		user.setCreatedDate(new Date());

		try {

			// check if user is already in database
			createdUser = this.userDao.getUserByUsername(user.getUsername());

			if (createdUser != null)
				throw new UserAlreadyCreatedException();

			createdUser = this.userDao.createUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new DaoException("Cannot save a new user.", e);
		}

		return createdUser;

	}

	public Integer deleteUser(Integer id) throws DaoException {

		if (id == null)
			return 0;

		try {
			this.userDao.deleteUser(id);
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new DaoException("Cannot delete user.", e);
		}

	}

}
