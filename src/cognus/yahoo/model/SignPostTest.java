package cognus.yahoo.model;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import android.annotation.SuppressLint;

/**
 * Sample code to use Yahoo! Search BOSS
 * 
 * Please include the following libraries 1. Apache Log4j 2. oAuth Signpost
 * 
 * @author xyz
 */
public class SignPostTest {

	protected static String yahooServer = "http://yboss.yahooapis.com/ysearch/";

	// Please provide your consumer key here
	private static String consumer_key = "";

	// Please provide your consumer secret here
	private static String consumer_secret = "";

	/** The HTTP request object used for the connection */
	private static StHttpRequest httpRequest = new StHttpRequest();

	/** Encode Format */
	private static final String ENCODE_FORMAT = "UTF-8";

	/** Call Type */
	private static final String callType = "web";

	private static final int HTTP_STATUS_OK = 200;

	/**
	 * 
	 * @return
	 */
	public int returnHttpData() throws UnsupportedEncodingException, Exception {

		if (this.isConsumerKeyExists() && this.isConsumerSecretExists()) {

			// Start with call Type
			String params = callType;

			// Add query
			params = params.concat("?q=");

			// Encode Query string before concatenating
			params = params.concat(URLEncoder.encode(this.getSearchString(),
					"UTF-8"));

			// Create final URL
			String url = yahooServer + params;

			// Create oAuth Consumer
			OAuthConsumer consumer = new DefaultOAuthConsumer(consumer_key,
					consumer_secret);

			// Set the HTTP request correctly
			httpRequest.setOAuthConsumer(consumer);

			try {
				System.out.println("sending get request to"
						+ URLDecoder.decode(url, ENCODE_FORMAT));
				int responseCode = httpRequest.sendGetRequest(url);

				// Send the request
				if (responseCode == HTTP_STATUS_OK) {
					System.out.println("Response ");
				} else {
					System.out
							.println("Error in response due to status code = "
									+ responseCode);
				}
				System.out.println(httpRequest.getResponseBody());

			} catch (UnsupportedEncodingException e) {
				System.out.println("Encoding/Decording error");
			} catch (IOException e) {
				System.out.println("Error with HTTP IO" + e);
			} catch (Exception e) {
				System.out.println(httpRequest.getResponseBody() + e);
				return 0;
			}

		} else {
			System.out.println("Key/Secret does not exist");
		}
		return 1;
	}

	private String getSearchString() {
		return "Yahoo";
	}

	@SuppressLint("NewApi")
	private boolean isConsumerKeyExists() {
		if (consumer_key.isEmpty()) {
			System.out
					.println("Consumer Key is missing. Please provide the key");
			return false;
		}
		return true;
	}

	@SuppressLint("NewApi")
	private boolean isConsumerSecretExists() {
		if (consumer_secret.isEmpty()) {
			System.out
					.println("Consumer Secret is missing. Please provide the key");
			return false;
		}
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {

			SignPostTest signPostTest = new SignPostTest();

			signPostTest.returnHttpData();

		} catch (Exception e) {
			System.out.println("Error" + e);
		}
	}

}