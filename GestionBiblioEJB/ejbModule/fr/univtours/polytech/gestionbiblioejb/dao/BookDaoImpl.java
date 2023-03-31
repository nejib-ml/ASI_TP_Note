package fr.univtours.polytech.gestionbiblioejb.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.univtours.polytech.gestionbiblioejb.model.Author;
import fr.univtours.polytech.gestionbiblioejb.model.Book;
import fr.univtours.polytech.gestionbiblioejb.model.Genre;

@Stateless
public class BookDaoImpl implements BookDao {
	@PersistenceContext(unitName = "GestionBiblioEJB")
	private EntityManager entityManager;
	
	@Inject
	private GenreDao genreDao;
	Genre genre;

	@Override
	public void create(Book book) {
		entityManager.persist(book);
	}

	@Override
	public Book findById(Long id) {
		return entityManager.find(Book.class, id);
	}

	@Override
	public List<Book> findAll() {
		TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b", Book.class);
		return query.getResultList();
	}

	@Override
	public List<Book> findByAuthor(Author author) {
		TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b WHERE b.author = :author", Book.class);
		query.setParameter("author", author);
		return query.getResultList();
	}

	@Override
	public List<Book> findByTitle(String title) {
		TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b WHERE b.title LIKE :title",
				Book.class);
		query.setParameter("title", "%" + title.toLowerCase() + "%");
		return query.getResultList();
	}

	@Override
	public List<Book> findByGenre(Genre genre) {
		TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b WHERE b.genre = :genre", Book.class);
		query.setParameter("genre", genre);
		return query.getResultList();
	}

	@Override
	public List<Book> findByAvailability(boolean available) {
		TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b WHERE b.available = :available",
				Book.class);
		query.setParameter("available", available);
		return query.getResultList();
	}

	@Override
	public List<Book> searchBooks(String authorName, String title, Long genreId, boolean availableOnly) {
		StringBuilder jpql = new StringBuilder("SELECT b FROM Book b WHERE 1=1");

		if (authorName != null && !authorName.isEmpty()) {
			jpql.append(" AND LOWER(b.author.name) LIKE :authorName");
		}

		if (title != null && !title.isEmpty()) {
			jpql.append(" AND LOWER(b.title) LIKE :title");
		}

		if (genreId != null) {
			genre = genreDao.findById(genreId);
			jpql.append(" AND b.genre = :genre");
		}

		if (availableOnly) {
			jpql.append(" AND b.available = TRUE");
		}

		TypedQuery<Book> query = entityManager.createQuery(jpql.toString(), Book.class);

		if (authorName != null && !authorName.isEmpty()) {
			query.setParameter("authorName", "%" + authorName.toLowerCase() + "%");
		}

		if (title != null && !title.isEmpty()) {
			query.setParameter("title", "%" + title.toLowerCase() + "%");
		}

		if (genreId != null) {
			Genre genre = genreDao.findById(genreId);
			query.setParameter("genre", genre);
		}

		return query.getResultList();
	}

	@Override
	public void updateBook(Book book) {
		entityManager.merge(book);
	}

	@Override
	public void delete(Book book) {
		entityManager.remove(entityManager.merge(book));
	}
}