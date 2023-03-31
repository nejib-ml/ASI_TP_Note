package fr.univtours.polytech.gestionbiblioejb.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.univtours.polytech.gestionbiblioejb.model.Book;
import fr.univtours.polytech.gestionbiblioejb.model.Loan;
import fr.univtours.polytech.gestionbiblioejb.model.User;

@Stateless
public class LoanDaoImpl implements LoanDao {
	@PersistenceContext(unitName = "GestionBiblioEJB")
	private EntityManager entityManager;
	
	@Inject
	BookDao bookDao;

	@Override
	public void create(Loan loan) {
		loan.getBook().setAvailable(false);
		
		Book book = loan.getBook();
		book.setAvailable(false);
		bookDao.updateBook(book);
		
		entityManager.persist(loan);
	}

	@Override
	public Loan findById(Long id) {
		return entityManager.find(Loan.class, id);
	}

	@Override
	public List<Loan> findByUser(User user) {
		TypedQuery<Loan> query = entityManager.createQuery("SELECT l FROM Loan l WHERE l.user = :user", Loan.class);
		query.setParameter("user", user);
		return query.getResultList();
	}

	@Override
	public List<Loan> findByBook(Book book) {
		TypedQuery<Loan> query = entityManager.createQuery("SELECT l FROM Loan l WHERE l.book = :book", Loan.class);
		query.setParameter("book", book);
		return query.getResultList();
	}

	@Override
	public List<Loan> getAllLoans() {
		TypedQuery<Loan> query = entityManager.createQuery("SELECT l FROM Loan l", Loan.class);
		return query.getResultList();
	}

	@Override
	public void update(Loan loan) {
		entityManager.merge(loan);
	}

	@Override
	public void delete(Loan loan) {
		entityManager.remove(entityManager.merge(loan));
	}
}