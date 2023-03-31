package fr.univtours.polytech.gestionbiblioweb.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.univtours.polytech.gestionbiblioejb.business.UserService;
import fr.univtours.polytech.gestionbiblioejb.model.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
			if (user != null && userService.verify(user.getMail(), user.getPassword()) != null) {
				response.sendRedirect(request.getContextPath() + "/");
				return;
			}
		}
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String mail = request.getParameter("mail");
		String password = request.getParameter("password");

		User user = userService.authenticate(mail, password);

		if (user != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("user", user);
			response.sendRedirect(request.getContextPath() + "/home");
		} else {
			request.setAttribute("error", "Mail ou mot de passe n'est pas correct !");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

}
