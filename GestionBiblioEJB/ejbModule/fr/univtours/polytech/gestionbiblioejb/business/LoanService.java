package fr.univtours.polytech.gestionbiblioejb.business;

import java.util.List;

import javax.ejb.Local;

import fr.univtours.polytech.gestionbiblioejb.model.Book;
import fr.univtours.polytech.gestionbiblioejb.model.Loan;
import fr.univtours.polytech.gestionbiblioejb.model.User;

@Local
public interface LoanService {
	void createLoan(Loan loan);

	Loan findLoanById(Long id);

	List<Loan> findLoansByUser(User user);

	List<Loan> findLoansByBook(Book book);

	List<Loan> getAllLoans();

	void updateLoan(Loan loan);

	void deleteLoan(Loan loan);
}
