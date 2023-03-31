package fr.univtours.polytech.gestionbiblioejb.business;

import java.sql.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import fr.univtours.polytech.gestionbiblioejb.dao.BookDao;
import fr.univtours.polytech.gestionbiblioejb.dao.LoanDao;
import fr.univtours.polytech.gestionbiblioejb.model.Book;
import fr.univtours.polytech.gestionbiblioejb.model.Loan;
import fr.univtours.polytech.gestionbiblioejb.model.User;

@Stateless
public class BookServiceImpl implements BookService {
	@Inject
	private BookDao bookDao;

	@Inject
	private LoanDao loanDao;

	@Override
	public void createBook(Book book) {
	    if (book != null) {
	    	bookDao.create(book);
	    }
	}
	
	@Override
	public Book findById(Long id) {
		return bookDao.findById(id);
	}
	
	@Override
	public void updateBook(Book book) {
		bookDao.updateBook(book);;
	}

	@Override
	public List<Book> getAllBooks() {
		return bookDao.findAll();
	}

	@Override
	public List<Book> searchBooks(String authorName, String title, Long genreId, boolean availableOnly) {
		List<Book> books = bookDao.searchBooks(authorName, title, genreId, availableOnly);
		return books;
	}

	@Override
	public void borrowBook(User user, Book book, Date startDate, Date endDate) {

		if (user == null) {
			System.out.println("User cannot be null");
		}

		if (book == null) {
			System.out.println("Book cannot be null");
		}

		// Vérification si le livre est disponible
		if (!book.isAvailable()) {
			System.out.println("Book is not available for borrowing");
		}

		// Vérification si l'utilisateur n'a pas atteint le nombre maximum d'emprunts
		if (loanDao.findByUser(user).size() >= 5) {
			System.out.println("Maximum number of loans reached");
		}

		// Création d'un objet Loan et enregistrement en base de données
		Loan loan = new Loan(book, user, startDate, endDate);
		loanDao.create(loan);

		// Mise à jour de l'état du livre
		book.setAvailable(false);
		bookDao.updateBook(book);
	}

}