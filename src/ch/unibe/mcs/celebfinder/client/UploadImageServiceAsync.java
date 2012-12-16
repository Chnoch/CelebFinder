package ch.unibe.mcs.celebfinder.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The client side stub for the RPC service.
 */
public interface UploadImageServiceAsync {
	void uploadImage(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;
}

