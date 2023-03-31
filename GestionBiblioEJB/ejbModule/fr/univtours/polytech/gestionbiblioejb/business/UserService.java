package fr.univtours.polytech.gestionbiblioejb.business;

import java.util.List;

import javax.ejb.Local;

import fr.univtours.polytech.gestionbiblioejb.model.User;

@Local
public interface UserService {
	public List<User> getAllUsers();
	
	public User getUserById(Long id);

	public User authenticate(String username, String password);
	
	public User verify(String username, String hashPassword);
	
	public User getUserByMail(String mail);

	public void createUser(User user);

	public void updateUser(User user);

	public void deleteUser(User user);
}