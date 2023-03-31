package fr.univtours.polytech.gestionbiblioejb.dao;

import java.util.List;

import fr.univtours.polytech.gestionbiblioejb.model.Book;
import fr.univtours.polytech.gestionbiblioejb.model.Loan;
import fr.univtours.polytech.gestionbiblioejb.model.User;

public interface LoanDao {
	public void create(Loan loan);

	public Loan findById(Long id);

	public List<Loan> findByUser(User user);

	public List<Loan> findByBook(Book book);

	public List<Loan> getAllLoans();

	public void update(Loan loan);

	public void delete(Loan loan);
}