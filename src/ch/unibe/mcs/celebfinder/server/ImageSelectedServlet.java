package ch.unibe.mcs.celebfinder.server;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;

import ch.unibe.mcs.celebfinder.controller.ImageController;
import ch.unibe.mcs.celebfinder.controller.PersonController;
import ch.unibe.mcs.celebfinder.controller.UserController;
import ch.unibe.mcs.celebfinder.model.CelebImage;
import ch.unibe.mcs.celebfinder.model.CelebUser;
import ch.unibe.mcs.celebfinder.model.Person;

public class ImageSelectedServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		long imageKey = Long.parseLong(req.getParameter("imageKey"));
		String personKey = req.getParameter("personSelection");

		// find desired image
		CelebImage image = ImageController.getCelebImageFromID(imageKey);
		Person person = PersonController.getPersonFromName(personKey);
		CelebUser user = UserController.getCelebUserFromAuth((User) req
				.getSession().getAttribute("user"));

		if (image.addCandidate(person)) {
			// success
			if (user != null)
				user.addScore(1);

			resp.sendRedirect("/success.jsp");

		} else {
			// failure
			resp.sendRedirect("/failure.jsp");
		}

		// find desired person
	}
}
