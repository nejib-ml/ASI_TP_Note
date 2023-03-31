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

import fr.univtours.polytech.gestionbiblioejb.business.GenreService;
import fr.univtours.polytech.gestionbiblioejb.business.UserService;
import fr.univtours.polytech.gestionbiblioejb.model.Genre;
import fr.univtours.polytech.gestionbiblioejb.model.User;

@WebServlet("/genre")
public class GenreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private GenreService genreService;

	@EJB
	private UserService userService;

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
				if (verifiedUser != null) {
					List<Genre> genres = genreService.getAllGenres();
					request.setAttribute("user", user);
					request.setAttribute("genres", genres);
					request.getRequestDispatcher("genre.jsp").forward(request, response);
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
					String genreName = request.getParameter("genreName");

					Genre genre = new Genre();
					genre.setName(genreName);

					genreService.createGenre(genre);

					List<Genre> genres = genreService.getAllGenres();
					request.setAttribute("user", user);
					request.setAttribute("genres", genres);
					request.getRequestDispatcher("genre.jsp").forward(request, response);
					return;
				}
			}
		}
		response.sendRedirect(request.getContextPath() + "/home");
	}

}
