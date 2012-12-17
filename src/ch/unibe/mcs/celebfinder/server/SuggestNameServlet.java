package ch.unibe.mcs.celebfinder.server;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.unibe.mcs.celebfinder.controller.PMF;
import ch.unibe.mcs.celebfinder.model.Candidate;
import ch.unibe.mcs.celebfinder.model.CelebImage;
import ch.unibe.mcs.celebfinder.model.Person;

public class SuggestNameServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		// Get the image representation
		String firstname = req.getParameter("firstname");
		String lastname = req.getParameter("lastname");
		long imageKey = Long.parseLong(req.getParameter("imageKey"));

		List<Person> results = getAvailablePerson(firstname, lastname);
		CelebImage image = pm.getObjectById(CelebImage.class, imageKey);

		Person person;
		if (results.isEmpty()) {
			person = new Person(firstname, lastname);
		} else {
			person = results.iterator().next();
		}
		Candidate candidate = new Candidate(image, person);
		image.addCandidate(person);

		person.save();
		candidate.save();
		image.save();
		pm.close();

		// respond to query
		resp.sendRedirect("/UploadImageForm.jsp");
	}

	private List<Person> getAvailablePerson(String firstname, String lastname) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Query q = pm.newQuery(Person.class, "lastName == '" + lastname
					+ "'" + " && firstName == '" + firstname + "'");

			List<Person> persons = (List<Person>) q.execute();
			return persons;
		} finally {
			pm.close();
		}
	}
}
