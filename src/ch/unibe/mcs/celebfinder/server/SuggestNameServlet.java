package ch.unibe.mcs.celebfinder.server;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
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

public class SuggestNameServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		CelebImage image;
		Person person;
		CelebUser user;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			user = UserController.getCelebUserFromAuth((User) req.getSession()
					.getAttribute("user"));

			// Get the image representation
			String firstname = req.getParameter("firstname");
			String lastname = req.getParameter("lastname");
			long imageKey = Long.parseLong(req.getParameter("imageKey"));

			// List<Person> results = PersonController.getAvailablePerson(
			// firstname, lastname);
			person = new Person(firstname, lastname);
			image = pm.getObjectById(CelebImage.class, imageKey);
		} finally {
			pm.close();
		}

		// Person person;
		// if (results.isEmpty()) {
		// person = new Person(firstname, lastname);
		// } else {
		// person = results.iterator().next();
		// }

		if (image != null && person != null) {
			image.addCandidate(person);
			person.save();
			image.save();

			if (user != null)
				user.addScore(5);
		}
		// respond to query
		resp.sendRedirect("/UploadImageForm.jsp");
	}

}
