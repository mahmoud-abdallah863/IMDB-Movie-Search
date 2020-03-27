package com.example.imdbsearch.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.imdbsearch.R;
import com.example.imdbsearch.ui.fragments.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new MainFragment())
                .commit();
    }
}
