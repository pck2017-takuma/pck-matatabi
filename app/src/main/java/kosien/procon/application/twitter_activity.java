package kosien.procon.application;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import su.heartlove.matatabi.R;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class twitter_activity extends AppCompatActivity {

    private String mCallbackIRL;
    private Twitter mTwitter;
    private RequestToken mRequestToken;

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_setting);

        mCallbackIRL = getString(R.string.twitter_callback_url);
        mTwitter = TwitterUtils.getTwitterInstance(this);

        //OAuth認証してるか判定
        final Boolean booltwitter = (!TwitterUtils.hasAccessToken(this));

        //してなかったら認証画面に行く
        //してたりしてなかったりするとボタンの文字が変わる
        Button txtButton = (Button) findViewById(R.id.twitter_button);
        if (booltwitter) {
            txtButton.setText(R.string.oauth_text);
            findViewById(R.id.twitter_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startAuthorise();
                }
            });
        } else {
            txtButton.setText(R.string.tweet_text);
        }


    }

    private void startAuthorise() {
        @SuppressLint("StaticFieldLeak")
        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                try {
                    mRequestToken = mTwitter.getOAuthRequestToken(mCallbackIRL);
                    return mRequestToken.getAuthorizationURL();
                } catch (TwitterException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String url) {
                if (url != null) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                }
            }
        };
        task.execute();
    }

    //認証するところ
    @Override
    public void onNewIntent(Intent intent) {
        if (intent == null || intent.getData() == null || !intent.getData().toString().startsWith(mCallbackIRL)) {
            return;
        }

        String verifier = intent.getData().getQueryParameter("oauth_verifier");

        @SuppressLint("StaticFieldLeak")
        AsyncTask<String, Void, AccessToken> task = new AsyncTask<String, Void, AccessToken>() {
            @Override
            protected AccessToken doInBackground(String... params) {
                try {
                    return mTwitter.getOAuthAccessToken(mRequestToken, params[0]);
                } catch (TwitterException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(AccessToken accessToken) {
                if (accessToken != null) {
                    showToast("認証成功");
                    successOAuth(accessToken);
                } else {
                    showToast("認証失敗");
                }
            }
        };
        task.execute(verifier);
    }

    private void successOAuth(AccessToken accessToken) {
        TwitterUtils.storeAccessToken(this, accessToken);
        Intent intent = new Intent(this, twitter_activity.class);
        startActivity(intent);
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

}







