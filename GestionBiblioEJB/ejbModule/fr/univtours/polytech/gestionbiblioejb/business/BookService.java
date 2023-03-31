package fr.univtours.polytech.gestionbiblioejb.business;

import java.sql.Date;
import java.util.List;

import javax.ejb.Local;

import fr.univtours.polytech.gestionbiblioejb.model.Book;
import fr.univtours.polytech.gestionbiblioejb.model.User;

@Local
public interface BookService {

	public void createBook(Book book);
	
	public Book findById(Long id);

	public void updateBook(Book book);

	public List<Book> getAllBooks();

	public List<Book> searchBooks(String authorName, String title, Long genreId, boolean availableOnly);

	public void borrowBook(User user, Book book, Date startDate, Date endDate);

}
