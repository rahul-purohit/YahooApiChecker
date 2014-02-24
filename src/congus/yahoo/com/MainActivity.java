package congus.yahoo.com;

import java.util.Scanner;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.YahooApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	private static final String PROTECTED_RESOURCE_URL = "http://social.yahooapis.com/v1/user/A6ROU63MXWDCW3Y5MGCYWVHDJI/profile/status?format=json";
	WebView webView;
	Button button;
	OAuthService service;
	EditText txt;
	Token requestToken;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		webView = (WebView) findViewById(R.id.webView1);
		button = (Button) findViewById(R.id.button1);
		txt = (EditText) findViewById(R.id.editText1);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Checker checker = new Checker();
				checker.execute("");
			}
		});
		Loader loader = new Loader();
		loader.execute("");

	}

	private class Loader extends AsyncTask<String, String, String>

	{

		@Override
		protected String doInBackground(String... params) {
			try {
				service = new ServiceBuilder()
						.provider(YahooApi.class)
						.apiKey("dj0yJmk9cXFUTUplZGRHTXJCJmQ9WVdrOWF6TTVhWFJMTjJjbWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmeD01Yg--")
						.apiSecret("d756f83d1a01f4241b09171ad0295733f978dfcb")
						.build();
				Scanner in = new Scanner(System.in);

				System.out.println("=== Yahoo's OAuth Workflow ===");
				System.out.println();

				// Obtain the Request Token
				System.out.println("Fetching the Request Token...");
				requestToken = service.getRequestToken();
				System.out.println("Got the Request Token!");
				System.out.println();

				System.out.println("Now go and authorize Scribe here:");
				System.out.println(service.getAuthorizationUrl(requestToken));
				publishProgress(service.getAuthorizationUrl(requestToken));
				System.out.println("And paste the verifier here");
				System.out.print(">>");

				in.close();
			} catch (Exception exception) {
				exception.printStackTrace();

			}

			return null;
		}

		@Override
		protected void onProgressUpdate(String... values) {
			webView.loadUrl(values[0]);

			super.onProgressUpdate(values);
		}
	}

	private class Checker extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			Verifier verifier = new Verifier(txt.getText().toString());
			System.out.println();

			// Trade the Request Token and Verfier for the Access Token
			System.out
					.println("Trading the Request Token for an Access Token...");
			Token accessToken = service.getAccessToken(requestToken, verifier);
			System.out.println("Got the Access Token!");
			System.out.println("(if your curious it looks like this: "
					+ accessToken + " )");
			System.out.println();

			// Now let's go and ask for a protected resource!
			System.out
					.println("Now we're going to access a protected resource...");
			OAuthRequest request = new OAuthRequest(Verb.GET,
					PROTECTED_RESOURCE_URL);
			service.signRequest(accessToken, request);
			Response response = request.send();
			System.out.println("Got it! Lets see what we found...");
			System.out.println();
			System.out.println(response.getCode());
			System.out.println(response.getBody());

			System.out.println();
			System.out
					.println("Thats it man! Go and build something awesome with Scribe! :)");
			return null;
		}

	}
}
