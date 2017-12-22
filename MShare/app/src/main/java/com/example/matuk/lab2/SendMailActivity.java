package com.example.matuk.lab2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by matuk on 2017-12-22.
 */

public class SendMailActivity extends AppCompatActivity {

    private static  final String TAG="mymsg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mail);
    }

    public void sendEmail(View view){
        final EditText email =  (EditText) findViewById(R.id.EditTextEmail);
        String title = email.getText().toString();

        Log.i("Send email", "");
        String[] TO = {"mshare@gmail.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Song submission");

        final EditText artistText =  (EditText) findViewById(R.id.EditTextArtist);
        String artist = artistText.getText().toString();

        final EditText linkText =  (EditText) findViewById(R.id.EditTextLink);
        String link = linkText.getText().toString();

        String text = "Title: " + title + "\nArtist: " + artist + "\nLink: " + link;
        emailIntent.putExtra(Intent.EXTRA_TEXT, text);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i(TAG, "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(SendMailActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
