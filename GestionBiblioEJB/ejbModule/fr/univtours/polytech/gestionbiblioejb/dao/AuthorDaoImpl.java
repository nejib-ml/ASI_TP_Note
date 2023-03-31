package fr.univtours.polytech.gestionbiblioejb.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.univtours.polytech.gestionbiblioejb.model.Author;

@Stateless
public class AuthorDaoImpl implements AuthorDao {
	@PersistenceContext(unitName = "GestionBiblioEJB")
	private EntityManager entityManager;

	@Override
	public void create(Author author) {
		entityManager.persist(author);
	}

	@Override
	public Author findById(Long id) {
		return entityManager.find(Author.class, id);
	}

	@Override
	public List<Author> findAll() {
		TypedQuery<Author> query = entityManager.createQuery("SELECT a FROM Author a", Author.class);
		return query.getResultList();
	}

	@Override
	public List<Author> findByName(String name) {
		TypedQuery<Author> query = entityManager.createQuery("SELECT a FROM Author a WHERE LOWER(a.name) LIKE :name",
				Author.class);
		query.setParameter("name", "%" + name.toLowerCase() + "%");
		return query.getResultList();
	}

	@Override
	public void update(Author author) {
		entityManager.merge(author);
	}

	@Override
	public void delete(Author author) {
		entityManager.remove(entityManager.merge(author));
	}
}
