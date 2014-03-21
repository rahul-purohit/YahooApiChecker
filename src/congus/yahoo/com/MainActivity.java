package congus.yahoo.com;

import org.json.JSONArray;
import org.json.JSONObject;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.YahooApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class MainActivity extends Activity {
	final static String APIKEY = "dj0yJmk9cXFUTUplZGRHTXJCJmQ9WVdrOWF6TTVhWFJMTjJjbWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmeD01Yg--";
	final static String APISECRET = "d756f83d1a01f4241b09171ad0295733f978dfcb";
	final static String CALLBACK = "http://www.cognustechnology.com/";
	static OAuthService s;
	static Token requestToken = null;
	WebView webview;
	EditText textView;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textView = (EditText) findViewById(R.id.EditText1);
		webview = (WebView) findViewById(R.id.webView1);
		webview.getSettings().setJavaScriptEnabled(true);
		webview.getSettings().setJavaScriptEnabled(true);
		webview.setWebViewClient(new WebViewClient());
		WebSettings webSettings = webview.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setDomStorageEnabled(true);
		webview.getSettings().setJavaScriptEnabled(true);
		webview.getSettings().setDomStorageEnabled(true);
		webview.getSettings().setDomStorageEnabled(true);
		if (APIKEY == null || APISECRET == null) {
			textView.setText("You must enter your own APIKEY and SECRET to use this demo.");
			webview.setVisibility(View.GONE);
			return;
		}

		webview.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {

				// check for our custom callback protocol otherwise use
				// default
				// behavior
				if (url.startsWith("http://www.cognustechnology.com/")) {
					// authorization complete hide webview for now.
//					webview.setVisibility(View.GONE);

					Uri uri = Uri.parse(url);
					Loader loader = new Loader();
					loader.execute(uri);
					return true;
				}

				return super.shouldOverrideUrlLoading(view, url);
			}
		});
		DoAuthentication authentication = new DoAuthentication();
		authentication.execute("");

		if (s != null) {
			String authURL = s.getAuthorizationUrl(requestToken);
			webview.loadUrl(authURL);
		}

	}

	private class DoAuthentication extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			s = new ServiceBuilder().provider(YahooApi.class).apiKey(APIKEY)
					.apiSecret(APISECRET).callback(CALLBACK).build();

			requestToken = s.getRequestToken();
			String authURL = s.getAuthorizationUrl(requestToken);

			return authURL;
		}

		@Override
		protected void onPostExecute(String result) {
			webview.loadUrl(result);

			super.onPostExecute(result);
		}

	}

	private class Loader extends AsyncTask<Uri, String, JSONObject> {

		@Override
		protected JSONObject doInBackground(Uri... params) {
			
			
			s = new ServiceBuilder().provider(YahooApi.class).apiKey(APIKEY)
					.apiSecret(APISECRET).callback(CALLBACK).build();

			requestToken = s.getRequestToken();
			
			
			String verifier = params[0].getQueryParameter("oauth_verifier");
			Verifier v = new Verifier(verifier);
			// save this token for practical use.
			Token accessToken = s.getAccessToken(requestToken, v);

			OAuthRequest req = new OAuthRequest(
					Verb.GET,
					"http://fantasysports.yahooapis.com/fantasy/v2/league/328.l.85646/draftresults?format=json&is_available=true");
			s.signRequest(accessToken, req);

			Response response = req.send();
			JSONObject jsonObject = null;
			try {
				jsonObject = new JSONObject(response.getBody());
			} catch (Exception e) {
				// e.printStackTrace();
			}
			return jsonObject;
		}

		@Override
		protected void onPostExecute(JSONObject result) {
			try {
				Log.d("name", result.toString());
				System.out.println(result + "END");

				JSONObject fantasy_content = result
						.getJSONObject("fantasy_content");

				JSONObject users = fantasy_content.getJSONObject("users")
						.getJSONObject("0");

				JSONArray array = users.getJSONArray("user");

				for (int i = 0; i < array.length(); i++) {
					Object object = array.get(i);

					// This Key Will ensure Form This We Get A Array
					JSONObject object2 = (JSONObject) object;
					String key = "games";

					JSONArray jsonArray = object2.getJSONArray(key);
					for (int k = 0; k < jsonArray.length(); k++) {
						JSONObject object3 = (JSONObject) jsonArray.get(k);
						textView.setText(textView.getText()
								+ object3.toString() + "\n");
					}
				}

			} catch (Exception exception) {
				// exception.printStackTrace();
			}
			super.onPostExecute(result);
		}
	}

	public void printall(JSONObject result) {

	}

}