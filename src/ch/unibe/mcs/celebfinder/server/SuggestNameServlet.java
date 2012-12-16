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
		String imageKey = req.getParameter("imageKey");

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

		image.save();
		person.save();
		candidate.save();

		// respond to query
		resp.setContentType("text/plain");
		resp.getOutputStream().write("OK!".getBytes());
	}

	private List<Person> getAvailablePerson(String firstname, String lastname) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery("select from MyImage "
				+ "where name = firstnameParam " + "lastname = lastnameParam");

		return (List<Person>) query.execute(firstname, lastname);
	}
}
