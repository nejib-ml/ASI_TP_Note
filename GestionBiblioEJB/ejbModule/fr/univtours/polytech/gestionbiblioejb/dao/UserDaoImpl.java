package fr.univtours.polytech.gestionbiblioejb.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.univtours.polytech.gestionbiblioejb.model.User;
import fr.univtours.polytech.gestionbiblioejb.utils.MD5;

@Stateless
public class UserDaoImpl implements UserDao {
	@PersistenceContext(unitName = "GestionBiblioEJB")
	private EntityManager entityManager;

	@Override
	public void create(User user) {
		user.setPassword(MD5.getMd5(user.getPassword()));
		entityManager.persist(user);
	}

	@Override
	public User findById(Long id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public User authenticate(String mail, String password) {
		String hashPassword = MD5.getMd5(password);
		User user = findByMail(mail);

		if (user == null) {
			return null;
		}

		else if (user.getPassword().equals(hashPassword)) {
			return user;
		}

		else {
			return null;
		}
	}

	@Override
	public User verify(String mail, String hashPassword) {
		User user = findByMail(mail);

		if (user == null) {
			return null;
		}

		else if (user.getPassword().equals(hashPassword)) {
			return user;
		}

		else {
			return null;
		}
	}

	@Override
	public User findByMail(String mail) {
		TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.mail = :mail",
				User.class);
		query.setParameter("mail", mail);
		List<User> users = query.getResultList();
		return users.isEmpty() ? null : users.get(0);
	}

	@Override
	public List<User> findAll() {
		TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
		return query.getResultList();
	}

	@Override
	public void update(User user) {
		entityManager.merge(user);
	}

	@Override
	public void delete(User user) {
		entityManager.remove(entityManager.merge(user));
	}

}
