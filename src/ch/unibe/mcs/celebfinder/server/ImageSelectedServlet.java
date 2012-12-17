package ch.unibe.mcs.celebfinder.server;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.unibe.mcs.celebfinder.controller.ImageController;
import ch.unibe.mcs.celebfinder.controller.PersonController;
import ch.unibe.mcs.celebfinder.model.CelebImage;
import ch.unibe.mcs.celebfinder.model.Person;

public class ImageSelectedServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		long imageKey = Long.parseLong(req.getParameter("key"));
		long personKey = Long.parseLong(req.getParameter("personSelection"));
		
		// find desired image
		CelebImage image = ImageController.getCelebImageFromID(imageKey);
		Person person = PersonController.getPersonFromID(personKey);
		
		
		if (image.getCandidates().contains(person)) {
			// success
			resp.sendRedirect("/success.jsp");
			
		} else {
			// failure
			resp.sendRedirect("/failure.jsp");
		}
		
		// find desired person
	}
}
