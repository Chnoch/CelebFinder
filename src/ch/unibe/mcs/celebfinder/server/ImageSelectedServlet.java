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
		String personName = req.getParameter("personSelection");

		// find desired image
		CelebImage image = ImageController.getCelebImageFromID(imageKey);
		// Person person = PersonController.getPersonFromName(personKey);
		String[] personNameArray = personName.split(" ");
		Person person = new Person(personNameArray[0], personNameArray[1]);
		CelebUser user = UserController.getCelebUserFromAuth((User) req
				.getSession().getAttribute("user"));

		try {
			if (image.addCandidate(person)) {
				// success
				if (user != null)
					user.addScore(1);

				req.setAttribute("success", true);
				req.getRequestDispatcher("MainImageSelection.jsp").forward(req,
						resp);
			} else {
				// failure
				req.setAttribute("success", false);
				req.getRequestDispatcher("MainImageSelection.jsp").forward(req,
						resp);
			}
		} catch (Exception e) {

		}
	}
}
