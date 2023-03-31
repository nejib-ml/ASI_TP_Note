package fr.univtours.polytech.gestionbiblioweb.controller;

import java.io.IOException;
import java.util.List;

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

@WebServlet(name = "returnBookServlet", urlPatterns = { "returnBook" })
public class ReturnBook extends HttpServlet {

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

		// Vérifier si l'utilisateur est connecté et si c'est un administrateur
		HttpSession session = request.getSession(false);
		User user = null;
		if (session != null) {
			user = (User) session.getAttribute("user");
			if (user != null) {
				User verifiedUser = userService.verify(user.getMail(), user.getPassword());
				if (verifiedUser != null && verifiedUser.isAdmin()) {
					// Rediriger vers returnBook
					List<Loan> loans = loanService.getAllLoans();
					request.setAttribute("loans", loans);
					request.getRequestDispatcher("/returnBook.jsp").forward(request, response);
					return;
				}
			}
		}
		response.sendRedirect("login.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Vérifier si l'utilisateur est connecté et si c'est un administrateur
		HttpSession session = request.getSession(false);
		User user = null;
		if (session != null) {
			user = (User) session.getAttribute("user");
			if (user != null) {
				User verifiedUser = userService.verify(user.getMail(), user.getPassword());
				if (verifiedUser != null && verifiedUser.isAdmin()) {

					// Récupérer l'id de l'emprunte
					Long loanId = Long.parseLong(request.getParameter("loanId"));

					// Récupérer l'emprunte
					Loan loan = loanService.findLoanById(loanId);
					
					// Rendre le livre disponible
					Book book = loan.getBook();
					book.setAvailable(true);
					bookService.updateBook(book);
					loanService.deleteLoan(loan);

					// Rediriger vers returnBook
					List<Loan> loans = loanService.getAllLoans();
					request.setAttribute("loans", loans);
					request.getRequestDispatcher("/returnBook.jsp").forward(request, response);
					return;
				}
			}
		}
		response.sendRedirect("login.jsp");
	}
}
