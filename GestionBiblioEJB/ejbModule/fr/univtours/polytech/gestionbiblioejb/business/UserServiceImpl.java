package fr.univtours.polytech.gestionbiblioejb.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import fr.univtours.polytech.gestionbiblioejb.dao.UserDao;
import fr.univtours.polytech.gestionbiblioejb.model.User;

@Stateless
public class UserServiceImpl implements UserService {
	@Inject
	private UserDao userDao;

	@Override
	public List<User> getAllUsers() {
		return userDao.findAll();
	}
	
	@Override
	public User getUserById(Long id) {
		return userDao.findById(id);
	}
	
	@Override
	public User authenticate(String mail, String password) {
		return userDao.authenticate(mail, password);
	}
	
	@Override
	public User verify(String mail, String hashPassword) {
		return userDao.verify(mail, hashPassword);
	}

	@Override
	public User getUserByMail(String mail) {
		return userDao.findByMail(mail);
	}

	@Override
	public void createUser(User user) {
		userDao.create(user);
	}

	@Override
	public void updateUser(User user) {
		userDao.update(user);
	}

	@Override
	public void deleteUser(User user) {
		userDao.delete(user);
	}
}
