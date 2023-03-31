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

import fr.univtours.polytech.gestionbiblioejb.business.UserService;
import fr.univtours.polytech.gestionbiblioejb.model.User;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private UserService userService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Vérifier si l'utilisateur est connecté et si il est un admin
		HttpSession session = request.getSession(false);
		User user = null;
		if (session != null) {
			user = (User) session.getAttribute("user");
			if (user != null) {
				User verifiedUser = userService.verify(user.getMail(), user.getPassword());
				if (verifiedUser != null && verifiedUser.isAdmin()) {
					List<User> users = userService.getAllUsers();
					request.setAttribute("user", user);
					request.setAttribute("users", users);
					request.getRequestDispatcher("/users.jsp").forward(request, response);
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

					String firstname = request.getParameter("firstname");
					String lastname = request.getParameter("lastname");
					String mail = request.getParameter("mail");
					String password = request.getParameter("password");
					String role = request.getParameter("role");

					User alreadyExsits = null;
					alreadyExsits = userService.getUserByMail(mail);
					if (alreadyExsits == null) {
						User newUser = new User();
						newUser.setFirstname(firstname);
						newUser.setLastname(lastname);
						newUser.setMail(mail);
						newUser.setPassword(password);
						newUser.setAdmin(Boolean.getBoolean(role));

						userService.createUser(newUser);

						List<User> users = userService.getAllUsers();
						request.setAttribute("users", users);
						request.getRequestDispatcher("/users.jsp").forward(request, response);
					} else {
						request.setAttribute("error", "Veillez utiliser une autre adresse mail!");
						request.getRequestDispatcher("/register.jsp").forward(request, response);
					}
					return;
				}
			}
		}
		response.sendRedirect(request.getContextPath() + "/home");
	}

}
