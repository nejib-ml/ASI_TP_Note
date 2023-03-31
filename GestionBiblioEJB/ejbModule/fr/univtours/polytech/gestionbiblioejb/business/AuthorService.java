package fr.univtours.polytech.gestionbiblioejb.business;

import java.util.List;

import javax.ejb.Local;

import fr.univtours.polytech.gestionbiblioejb.model.Author;

@Local
public interface AuthorService {
	public List<Author> getAllAuthors();

	public Author getAuthorById(Long id);

	public void createAuthor(Author author);

	public void updateAuthor(Author author);

	public void deleteAuthor(Author author);
}