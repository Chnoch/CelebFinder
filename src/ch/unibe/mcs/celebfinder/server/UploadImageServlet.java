package ch.unibe.mcs.celebfinder.server;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import ch.unibe.mcs.celebfinder.controller.UserController;
import ch.unibe.mcs.celebfinder.model.CelebImage;
import ch.unibe.mcs.celebfinder.model.CelebUser;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;
import com.google.appengine.api.users.User;

public class UploadImageServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		// Get the image representation
		ImagesService imagesService = ImagesServiceFactory.getImagesService();
		try {
			ServletFileUpload upload = new ServletFileUpload();
			FileItemIterator iter = upload.getItemIterator(req);
			FileItemStream imageItem = iter.next();
			InputStream imgStream = imageItem.openStream();
			byte[] oldImageData = IOUtils.toByteArray(imgStream);

			Image oldImage = ImagesServiceFactory.makeImage(oldImageData);
			Transform resize = ImagesServiceFactory.makeResize(600, 800);

			Image newImage = imagesService.applyTransform(resize, oldImage);

			// construct our entity objects
			Blob imageBlob = new Blob(newImage.getImageData());
			CelebImage image = new CelebImage(imageBlob);

			image.save();

			CelebUser user = UserController.getCelebUserFromAuth((User) req
					.getSession().getAttribute("user"));
			if (user != null)
				user.addScore(5);
			// Redirect to suggest name form
			req.setAttribute("imageKey", image.getKey().getId());
			req.getRequestDispatcher("SuggestNameForm.jsp").forward(req, resp);
		} catch (Exception e) {
			resp.setContentType("text/plain");

			resp.getOutputStream().write("Nope!".getBytes());
		}
	}

}
