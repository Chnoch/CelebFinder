package ch.unibe.mcs.celebfinder.server;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;

import ch.unibe.mcs.celebfinder.controller.PMF;
import ch.unibe.mcs.celebfinder.controller.PersonController;
import ch.unibe.mcs.celebfinder.controller.UserController;
import ch.unibe.mcs.celebfinder.model.Candidate;
import ch.unibe.mcs.celebfinder.model.CelebImage;
import ch.unibe.mcs.celebfinder.model.CelebUser;
import ch.unibe.mcs.celebfinder.model.Person;

public class NewNameSuggestionServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			long imageKey = Long.parseLong(req.getParameter("imageKey"));

			req.setAttribute("imageKey", imageKey);
			req.getRequestDispatcher("SuggestNameForm.jsp").forward(req, resp);
			// respond to query
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pm.close();
		}
	}

}
