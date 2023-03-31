package fr.univtours.polytech.gestionbiblioejb.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import fr.univtours.polytech.gestionbiblioejb.business.LoanService;
import fr.univtours.polytech.gestionbiblioejb.model.Loan;
import fr.univtours.polytech.gestionbiblioejb.model.User;

public class LoansUtils {
	public static void alertUserLoans(HttpServletRequest request, User user, LoanService loanService) {
		List<Loan> loans = loanService.findLoansByUser(user);
		request.setAttribute("loans", loans);
		for (Loan loan : loans) {
			LocalDate dueDate = loan.getEndDate().toLocalDate();
			LocalDate currentDate = LocalDate.now();
			long daysBetween = ChronoUnit.DAYS.between(currentDate, dueDate);
			if (daysBetween < 7) {
				request.setAttribute("dueSoon", true);
				break;
			}
		}
	}

	public static Map<Loan, Boolean> loansDueDates(User user, LoanService loanService) {
		Map<Loan, Boolean> loansDueDates = new HashMap<Loan, Boolean>();
		List<Loan> loans = loanService.findLoansByUser(user);
		for (Loan loan : loans) {
			LocalDate dueDate = loan.getEndDate().toLocalDate();
			LocalDate currentDate = LocalDate.now();
			long daysBetween = ChronoUnit.DAYS.between(currentDate, dueDate);
			if (daysBetween >= 0 && daysBetween < 7) {
				loansDueDates.put(loan, true);
			}
			else {
				loansDueDates.put(loan, false);
			}
		}
		return loansDueDates;
	}
	
	public static Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return new java.sql.Date(cal.getTimeInMillis());
	}
}
