package fr.univtours.polytech.gestionbiblioejb.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import fr.univtours.polytech.gestionbiblioejb.dao.AuthorDao;
import fr.univtours.polytech.gestionbiblioejb.model.Author;

@Stateless
public class AuthorServiceImpl implements AuthorService {
	@Inject
	private AuthorDao authorDao;

	@Override
	public List<Author> getAllAuthors() {
		return authorDao.findAll();
	}

	@Override
	public Author getAuthorById(Long id) {
		return authorDao.findById(id);
	}

	@Override
	public void createAuthor(Author author) {
		authorDao.create(author);
	}

	@Override
	public void updateAuthor(Author author) {
		authorDao.update(author);
	}

	@Override
	public void deleteAuthor(Author author) {
		authorDao.delete(author);
	}
}