package com.example.sportbot;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import io.kommunicate.KmConversationBuilder;
import io.kommunicate.Kommunicate;
import io.kommunicate.callbacks.KmCallback;



public class MainActivity extends AppCompatActivity {


String APP_ID;
FloatingActionButton button;
ImageButton like,dislike;
EditText etext;
ImageButton go;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        APP_ID = "df220bf3cb6b875843ed3742f69570d";
        Kommunicate.init(MainActivity.this, APP_ID);
        button = (FloatingActionButton)findViewById(R.id.ringbutton);
        pd = new ProgressDialog(this);
        like = (ImageButton) findViewById(R.id.like);
        dislike = (ImageButton) findViewById(R.id.dislike);
        etext = (EditText)findViewById(R.id.etext);
        go= (ImageButton)findViewById(R.id.go);
        etext.setActivated(false);
        etext.setVisibility(View.INVISIBLE);
        go.setActivated(false);
        go.setVisibility(View.INVISIBLE);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                go.setActivated(false);
                etext.setActivated(false);
                etext.setVisibility(View.INVISIBLE);
                go.setVisibility(View.INVISIBLE);
                pd.setTitle("Initializing");
                pd.setMessage("Please wait while we assign you a Bot.");
                pd.setCanceledOnTouchOutside(false);
                pd.show();
                new KmConversationBuilder(MainActivity.this)
                        .setSingleConversation(false)
                        .launchConversation(new KmCallback() {
                            @Override
                            public void onSuccess(Object message) {
                                pd.dismiss();
                                Log.d("Conversation", "Success : " + message);
                            }

                            @Override
                            public void onFailure(Object error) {
                                pd.dismiss();
                                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                                Log.d("Conversation", "Failure : " + error);
                            }
                        });


            }
        });
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Feedback submitted", Toast.LENGTH_SHORT).show();
                etext.setActivated(false);
                etext.setVisibility(View.INVISIBLE);
                go.setActivated(false);
                go.setVisibility(View.INVISIBLE);
            }
        });
        dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Search the web", Toast.LENGTH_SHORT).show();
                etext.setVisibility(View.VISIBLE);
                etext.setActivated(true);
                go.setActivated(true);
                go.setVisibility(View.VISIBLE);
                go.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String query = etext.getText().toString();
                        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH );
                        intent.putExtra(SearchManager.QUERY, query);
                        startActivity(intent);
                    }
                });


            }
        });

    }


}
