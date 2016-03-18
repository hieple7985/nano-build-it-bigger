package com.hieple.droid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.udacity.gradle.imageactivity.R;

public class JokesDisplayActivity extends AppCompatActivity {

    public final static String JOKE_INTENT = "JOKE_INTENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jokes_display_activity);

        String joke = getIntent().getStringExtra(JOKE_INTENT);
        TextView textViewJoke = (TextView) findViewById(R.id.tvJoke);
        textViewJoke.setText(joke);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_about) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
