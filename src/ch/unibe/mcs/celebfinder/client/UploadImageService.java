package ch.unibe.mcs.celebfinder.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("uploadImage")
public interface UploadImageService extends RemoteService {
	String uploadImage(String name) throws IllegalArgumentException;
}
