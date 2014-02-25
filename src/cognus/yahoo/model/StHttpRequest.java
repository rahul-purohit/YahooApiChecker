package cognus.yahoo.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.util.Log;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

/**
 * @author David Hardtke
 * @author xyz Simple HTTP Request implementation
 */
public class StHttpRequest {
	Log log;
public static void main(String[] args) {
	
}
	private String responseBody = "";

	private OAuthConsumer consumer = null;

	/** Default Constructor */
	public StHttpRequest() {
	}

	public StHttpRequest(OAuthConsumer consumer) {
		this.consumer = consumer;
	}

	public HttpURLConnection getConnection(String url) throws IOException,
			OAuthMessageSignerException, OAuthExpectationFailedException,
			OAuthCommunicationException {
		try {
			URL u = new URL(url);

			HttpURLConnection uc = (HttpURLConnection) u.openConnection();

			if (consumer != null) {
				try {
					Log.d("Signing the oAuth consumer",
							"Signing the oAuth consumer");
					consumer.sign(uc);

				} catch (OAuthMessageSignerException e) {
					Log.d("Error",
							"Error signing the consumer" + e.getMessage());
					throw e;

				} catch (OAuthExpectationFailedException e) {
					Log.d("Error signing the consumer", e.getMessage());
					throw e;

				} catch (OAuthCommunicationException e) {
					Log.d("Error",
							"Error signing the consumer" + e.getMessage());
					throw e;
				}
				uc.connect();
			}
			return uc;
		} catch (IOException e) {
			Log.d("Error", "Error signing the consumer" + e.getMessage());
			throw e;
		}
	}

	/**
	 * Sends an HTTP GET request to a url
	 * 
	 * @param url
	 *            the url
	 * @return - HTTP response code
	 */
	public int sendGetRequest(String url) throws IOException,
			OAuthMessageSignerException, OAuthExpectationFailedException,
			OAuthCommunicationException {

		int responseCode = 500;
		try {
			HttpURLConnection uc = getConnection(url);

			responseCode = uc.getResponseCode();

			if (200 == responseCode || 401 == responseCode
					|| 404 == responseCode) {
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						responseCode == 200 ? uc.getInputStream()
								: uc.getErrorStream()));
				StringBuffer sb = new StringBuffer();
				String line;
				while ((line = rd.readLine()) != null) {
					sb.append(line);
				}
				rd.close();
				setResponseBody(sb.toString());
			}
		} catch (MalformedURLException ex) {
			throw new IOException(url + " is not valid");
		} catch (IOException ie) {
			throw new IOException("IO Exception " + ie.getMessage());
		}
		return responseCode;
	}

	/**
	 * Return the Response body
	 * 
	 * @return String
	 */
	public String getResponseBody() {
		return responseBody;
	}

	/**
	 * Setter
	 * 
	 * @param responseBody
	 */
	public void setResponseBody(String responseBody) {
		if (null != responseBody) {
			this.responseBody = responseBody;
		}
	}

	/**
	 * Set the oAuth consumer
	 * 
	 * @param consumer
	 */
	public void setOAuthConsumer(OAuthConsumer consumer) {
		this.consumer = consumer;
	}
}
