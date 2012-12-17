package ch.unibe.mcs.celebfinder.server;

import java.io.IOException;

import javax.servlet.http.*;

import ch.unibe.mcs.celebfinder.controller.UserController;
import ch.unibe.mcs.celebfinder.model.CelebUser;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class UserLoginServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		if (user != null) {
			CelebUser celebUser = UserController.getCelebUserFromAuth(user);
			if (celebUser == null) {
				celebUser = new CelebUser(user.getEmail(), user.getNickname());
				celebUser.save();
			}

			req.getSession().setAttribute("user", user);

			resp.sendRedirect("/index.jsp");
		} else {
			resp.sendRedirect(userService.createLoginURL(req.getRequestURI()));
		}
	}
}