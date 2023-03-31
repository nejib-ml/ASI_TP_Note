package fr.univtours.polytech.gestionbiblioejb.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import fr.univtours.polytech.gestionbiblioejb.dao.LoanDao;
import fr.univtours.polytech.gestionbiblioejb.model.Book;
import fr.univtours.polytech.gestionbiblioejb.model.Loan;
import fr.univtours.polytech.gestionbiblioejb.model.User;

@Stateless
public class LoanServiceImpl implements LoanService {

	@Inject
	private LoanDao loanDao;

	public void createLoan(Loan loan) {
		loanDao.create(loan);
	}

	public Loan findLoanById(Long id) {
		return loanDao.findById(id);
	}

	public List<Loan> findLoansByUser(User user) {
		return loanDao.findByUser(user);
	}

	public List<Loan> findLoansByBook(Book book) {
		return loanDao.findByBook(book);
	}

	public List<Loan> getAllLoans() {
		return loanDao.getAllLoans();
	}

	public void updateLoan(Loan loan) {
		loanDao.update(loan);
	}

	public void deleteLoan(Loan loan) {
		loanDao.delete(loan);
	}

}
