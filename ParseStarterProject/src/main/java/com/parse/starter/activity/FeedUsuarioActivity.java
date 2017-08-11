package com.parse.starter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.starter.R;
import com.parse.starter.adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

public class FeedUsuarioActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listView;
    private ArrayAdapter<ParseObject> adapter;
    private ArrayList<ParseObject> postagens;
    private String username;
    private String objectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_usuario);

        toolbar = (Toolbar) findViewById(R.id.toolbar_feed_usuario);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        objectId = intent.getStringExtra("objectId");

        toolbar.setTitle(username);
        toolbar.setTitleTextColor(getResources().getColor(R.color.preto));

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        postagens = new ArrayList<>();

        listView = (ListView) findViewById(R.id.lv_feed_usuario);
        adapter = new HomeAdapter(this, postagens);
        listView.setAdapter(adapter);

        getPostagens();
    }

    private void getPostagens() {
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Imagem");
        query.whereEqualTo("username", objectId);
        query.orderByDescending("createdAt");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null) {

                    if (objects.size() > 0) {
                        postagens.clear();

                        for (ParseObject postagem : objects) {
                            postagens.add(postagem);
                        }

                        adapter.notifyDataSetChanged();
                    }

                } else {
                    e.printStackTrace();
                }

            }
        });
    }

}
