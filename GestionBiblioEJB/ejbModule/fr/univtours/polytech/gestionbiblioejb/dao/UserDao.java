package fr.univtours.polytech.gestionbiblioejb.dao;

import java.util.List;

import fr.univtours.polytech.gestionbiblioejb.model.User;

public interface UserDao {
	public void create(User user);

	public User findById(Long id);

	public User findByMail(String mail);

	public List<User> findAll();

	public void update(User user);

	public void delete(User user);
	
	public User authenticate(String mail, String password);

	public User verify(String username, String hashPassword);
}