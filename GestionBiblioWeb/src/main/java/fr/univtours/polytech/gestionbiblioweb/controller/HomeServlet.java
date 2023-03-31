package fr.univtours.polytech.gestionbiblioweb.controller;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.univtours.polytech.gestionbiblioejb.business.BookService;
import fr.univtours.polytech.gestionbiblioejb.business.GenreService;
import fr.univtours.polytech.gestionbiblioejb.business.LoanService;
import fr.univtours.polytech.gestionbiblioejb.business.UserService;
import fr.univtours.polytech.gestionbiblioejb.model.Book;
import fr.univtours.polytech.gestionbiblioejb.model.Genre;
import fr.univtours.polytech.gestionbiblioejb.model.User;
import fr.univtours.polytech.gestionbiblioejb.utils.LoansUtils;

@WebServlet(name = "homeServlet", urlPatterns = { "/home" })
public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
	private BookService bookService;

	@EJB
	private GenreService genreService;

	@EJB
	private UserService userService;

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
				List<Book> books;

				// Charger la liste des livres disponibles à partir du DAO
				books = bookService.getAllBooks();

				// Charger les genres de la base de données
				List<Genre> genres = genreService.getAllGenres();

				// Mettre les résultats dans la requête
				request.setAttribute("genres", genres);
				request.setAttribute("books", books);
				request.setAttribute("user", user);

				// Récupérer les emprunts de l'utilisateur
				LoansUtils.alertUserLoans(request, user, loanService);

				// Forward vers la vue home.jsp
				RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
				dispatcher.forward(request, response);
				return;
			}
		}

		// Redirection vers la page de connexion
		response.sendRedirect(request.getContextPath() + "/login");

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
				// Récupérer les paramètres de recherche
				String authorName = request.getParameter("authorName");
				String title = request.getParameter("title");
				Long genreId = null;
				if (request.getParameter("genreId") != null) {
					if (!request.getParameter("genreId").equals("-1")) {
						genreId = Long.parseLong(request.getParameter("genreId"));
					}
				}

				boolean availableOnly = false;
				String availableOnlyParam = request.getParameter("availableOnly");
				if (availableOnlyParam != null) {
					if (availableOnlyParam.equals("on")) {
						availableOnly = true;
					}
				}

				// Effectuer la recherche de livres
				List<Book> books = bookService.searchBooks(authorName, title, genreId, availableOnly);
				List<Genre> genres = genreService.getAllGenres();

				// Mettre les résultats dans la requête
				request.setAttribute("books", books);
				request.setAttribute("genres", genres);
				request.setAttribute("authorName", authorName);
				request.setAttribute("title", title);
				request.setAttribute("genreId", genreId);
				request.setAttribute("availableOnly", availableOnly);

				// Forward vers la vue home.jsp
				request.getRequestDispatcher("home.jsp").forward(request, response);
				return;
			}
		}
		// Redirection vers la page de connexion
		response.sendRedirect(request.getContextPath() + "/login");

	}
}
