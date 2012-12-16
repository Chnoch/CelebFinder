package ch.unibe.mcs.celebfinder.server;

import ch.unibe.mcs.celebfinder.client.UploadImageService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class UploadImageServiceImpl extends RemoteServiceServlet implements
		UploadImageService {

	@Override
	public String uploadImage(String name) throws IllegalArgumentException {
		return null;
	}

}
