package fr.univtours.polytech.gestionbiblioejb.dao;

import java.util.List;

import fr.univtours.polytech.gestionbiblioejb.model.Author;
import fr.univtours.polytech.gestionbiblioejb.model.Book;
import fr.univtours.polytech.gestionbiblioejb.model.Genre;

public interface BookDao {
	public void create(Book book);

	public Book findById(Long id);

	public List<Book> findAll();

	public List<Book> findByAuthor(Author author);

	public List<Book> findByTitle(String title);

	public List<Book> findByGenre(Genre genre);

	public List<Book> findByAvailability(boolean available);
	
	public List<Book> searchBooks(String authorName, String title, Long genreId, boolean availableOnly);

	public void updateBook(Book book);

	public void delete(Book book);
}
