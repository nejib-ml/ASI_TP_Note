package fr.univtours.polytech.gestionbiblioweb.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.univtours.polytech.gestionbiblioejb.business.BookService;
import fr.univtours.polytech.gestionbiblioejb.business.LoanService;
import fr.univtours.polytech.gestionbiblioejb.business.UserService;
import fr.univtours.polytech.gestionbiblioejb.model.Book;
import fr.univtours.polytech.gestionbiblioejb.model.Loan;
import fr.univtours.polytech.gestionbiblioejb.model.User;
import fr.univtours.polytech.gestionbiblioejb.utils.LoansUtils;

@WebServlet(name = "borrowServlet", urlPatterns = { "borrow" })
public class BorrowServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
	private UserService userService;

	@EJB
	private BookService bookService;

	@EJB
	private LoanService loanService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Vérifier si l'utilisateur est connecté
		HttpSession session = request.getSession(false);
		User user = null;
		if (session != null) {
			user = (User) session.getAttribute("user");
			if (user != null && userService.verify(user.getMail(), user.getPassword()) != null) {
				// Rediriger vers la page d'emprunte
				Map<Loan, Boolean> loansDueDates = LoansUtils.loansDueDates(user, loanService);
				request.setAttribute("loansDueDates", loansDueDates);
				request.getRequestDispatcher("/loans.jsp").forward(request, response);
				return;
			}
		}
		response.sendRedirect("login.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Vérifier si l'utilisateur est connecté
		HttpSession session = request.getSession(false);
		User user = null;
		if (session != null) {
			user = (User) session.getAttribute("user");
			if (user != null && userService.verify(user.getMail(), user.getPassword()) != null) {

				// Récupérer l'id du livre à emprunter
				Long bookId = Long.parseLong(request.getParameter("bookId"));

				// Vérifier que le livre est disponible
				Book book = bookService.findById(bookId);
				if (!book.isAvailable()) {
					response.sendRedirect("home.jsp");
					return;
				}

				// Vérifier que l'utilisateur n'a pas atteint sa limite d'emprunts
				if (loanService.findLoansByUser(user).size() >= 5) {
					request.setAttribute("error", "Vous avez atteint votre limite d'emprunts (5 livres maximum).");
					// Rediriger vers la page d'emprunte
					Map<Loan, Boolean> loansDueDates = LoansUtils.loansDueDates(user, loanService);
					request.setAttribute("loansDueDates", loansDueDates);
					request.getRequestDispatcher("/loans.jsp").forward(request, response);
					return;
				}

				// Effectuer l'emprunt
				Date now = Date.valueOf(LocalDate.now());
				loanService.createLoan(new Loan(book, user, now, LoansUtils.addDays(now, 10)));

				// Rediriger vers la page d'emprunte
				Map<Loan, Boolean> loansDueDates = LoansUtils.loansDueDates(user, loanService);
				request.setAttribute("loansDueDates", loansDueDates);
				request.getRequestDispatcher("/loans.jsp").forward(request, response);
				return;
			}
		}
		response.sendRedirect("login.jsp");
	}
}
