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

import fr.univtours.polytech.gestionbiblioejb.business.AuthorService;
import fr.univtours.polytech.gestionbiblioejb.business.BookService;
import fr.univtours.polytech.gestionbiblioejb.business.GenreService;
import fr.univtours.polytech.gestionbiblioejb.business.UserService;
import fr.univtours.polytech.gestionbiblioejb.model.Author;
import fr.univtours.polytech.gestionbiblioejb.model.Book;
import fr.univtours.polytech.gestionbiblioejb.model.Genre;
import fr.univtours.polytech.gestionbiblioejb.model.User;

@WebServlet("/addBook")
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private GenreService genreService;

	@EJB
	private UserService userService;

	@EJB
	private AuthorService authorService;

	@EJB
	private BookService bookService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Vérifier si l'utilisateur est connecté
		HttpSession session = request.getSession(false);
		User user = null;
		if (session != null) {
			user = (User) session.getAttribute("user");
			if (user != null) {
				User verifiedUser = userService.verify(user.getMail(), user.getPassword());
				if (verifiedUser != null && verifiedUser.isAdmin()) {
					List<Genre> genres = genreService.getAllGenres();
					List<Author> authors = authorService.getAllAuthors();
					request.setAttribute("authors", authors);
					request.setAttribute("genres", genres);
					request.getRequestDispatcher("addBook.jsp").forward(request, response);
					return;
				}
			}

		}
		response.sendRedirect(request.getContextPath() + "/home");

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Vérifier si l'utilisateur est connecté et si il est un admin
		HttpSession session = request.getSession(false);
		User user = null;
		if (session != null) {
			user = (User) session.getAttribute("user");
			if (user != null) {
				User verifiedUser = userService.verify(user.getMail(), user.getPassword());
				if (verifiedUser != null && verifiedUser.isAdmin()) {
					String title = request.getParameter("title");
					Long genreId = Long.parseLong(request.getParameter("genreId"));
					Long authorId = Long.parseLong(request.getParameter("authorId"));

					Genre genre = genreService.getGenreById(genreId);
					Author author = authorService.getAuthorById(authorId);

					if (genre != null && author != null) {
						Book book = new Book();
						book.setAuthor(author);
						book.setGenre(genre);
						book.setAvailable(true);
						book.setTitle(title);

						bookService.createBook(book);
					}
				}
			}
		}
		response.sendRedirect(request.getContextPath() + "/home");
	}

}
