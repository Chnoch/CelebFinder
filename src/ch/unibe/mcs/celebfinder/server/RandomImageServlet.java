package ch.unibe.mcs.celebfinder.server;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.unibe.mcs.celebfinder.controller.RandomImagePicker;

import com.google.appengine.api.datastore.Blob;

public class RandomImageServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		// find desired image
		Blob image = RandomImagePicker.getRandomImage();

		// serve the first image
		resp.setContentType("image/jpeg");
		resp.getOutputStream().write(image.getBytes());
	}

}
