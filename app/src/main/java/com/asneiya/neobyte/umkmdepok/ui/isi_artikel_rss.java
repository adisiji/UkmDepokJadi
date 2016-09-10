package com.asneiya.neobyte.umkmdepok.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.asneiya.neobyte.umkmdepok.R;

/**
 * Created by neobyte on 8/25/2016.
 */
public class isi_artikel_rss extends AppCompatActivity{
    private static final String ARTICLE_URL = "ARTICLE_URL";
    private static final String TITLE = "TITLE";
    private ProgressDialog mProgressDialog;
    private Snackbar snackbar;
    private WebView webView;

    public static void launch(Context context, String articleUrl,String title) {
        Intent intent = new Intent(context, isi_artikel_rss.class);
        intent.putExtra(ARTICLE_URL, articleUrl);
        intent.putExtra(TITLE,title);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artikel_rss);
        CoordinatorLayout ll = (CoordinatorLayout) findViewById(R.id.root_webView);
        //set up web view
        webView = (WebView) findViewById(R.id.webview);
        //set up progress dialog
        mProgressDialog = new ProgressDialog(this);
        //set up snackbar
        snackbar = Snackbar
                .make(ll, "No internet connection!", Snackbar.LENGTH_LONG)
                .setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        webView.reload();
                    }
                });

        // Retrieve the Article URL from the Intent parameters
        String url = getIntent().getStringExtra(ARTICLE_URL);
        String title = getIntent().getStringExtra(TITLE);

        ActionBar ab = getSupportActionBar();
        if(ab!=null) {
            // Enable the Up button
            getSupportActionBar().setTitle(title);
            ab.setDisplayHomeAsUpEnabled(true);
        }

        // Load the URL into the WebView
        webView.getSettings().setSupportZoom(true);
        mProgressDialog.setMessage(getString(R.string.loading));
        mProgressDialog.show();
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                if(mProgressDialog!=null && mProgressDialog.isShowing())
                {
                    mProgressDialog.dismiss();
                }
            }

            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                error(view);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest description, WebResourceError failingUrl) {
                //Handle the error
                error(view);
                super.onReceivedError(view,description,failingUrl);
            }
        });

        webView.loadUrl(url);
    }

    private void error(WebView view){
        view.loadUrl("about:blank");
        mProgressDialog.dismiss();
        snackbar.show();
    }
}
