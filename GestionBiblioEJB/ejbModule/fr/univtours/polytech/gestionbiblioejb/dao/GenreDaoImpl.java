package fr.univtours.polytech.gestionbiblioejb.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.univtours.polytech.gestionbiblioejb.model.Genre;

@Stateless
public class GenreDaoImpl implements GenreDao {
	@PersistenceContext(unitName = "GestionBiblioEJB")
	private EntityManager entityManager;

	@Override
	public void create(Genre genre) {
		entityManager.persist(genre);
	}

	@Override
	public Genre findById(Long id) {
		return entityManager.find(Genre.class, id);
	}

	@Override
	public List<Genre> findAll() {
		TypedQuery<Genre> query = entityManager.createQuery("SELECT g FROM Genre g", Genre.class);
		return query.getResultList();
	}

	@Override
	public void update(Genre genre) {
		entityManager.merge(genre);
	}

	@Override
	public void delete(Genre genre) {
		entityManager.remove(entityManager.merge(genre));
	}
}
