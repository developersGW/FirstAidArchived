package developersgw.firstaid;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import static android.widget.Toast.*;
import static developersgw.firstaid.R.menu.menu_main;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        Context context = getApplicationContext();
        CharSequence text = "Herzlich Willkommen bei FirstAid von developersGW!";
        int duration = LENGTH_SHORT;

        Toast toast = makeText(context, text, duration);
        toast.show();

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
//if ()

        int showChangelog = sharedPref.getInt("showChangelog", 1);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        //alert.setTitle("Changelog");

        if(showChangelog==1) {
            WebView wv = new WebView(this);
            WebSettings webSettings = wv.getSettings();
            webSettings.setJavaScriptEnabled(true);
            wv.loadUrl("file:///android_res/raw/test.html");
            wv.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);

                    return true;
                }
            });

            alert.setView(wv);
            alert.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            });
            alert.show();
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("changelogShow", 0);
            editor.commit();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    public void openMapActivity(View view) {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }


    public void openMapActivity(MenuItem item) {
        makeText(getApplicationContext(), "Hier entsteht die MapActivity.", LENGTH_SHORT).show();
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }
}
