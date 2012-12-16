package ch.unibe.mcs.celebfinder.server;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.unibe.mcs.celebfinder.controller.RandomImagePicker;
import ch.unibe.mcs.celebfinder.model.CelebImage;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class GetImageServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String key = req.getParameter("key");
		Key k = KeyFactory.createKey(CelebImage.class.getSimpleName(), key);
		// find desired image
		Blob image = RandomImagePicker.getImageFromID(k);

		// serve the first image
		resp.setContentType("image/jpeg");
		resp.getOutputStream().write(image.getBytes());
	}

}
