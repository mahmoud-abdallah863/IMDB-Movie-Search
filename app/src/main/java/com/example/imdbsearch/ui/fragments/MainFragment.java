package com.example.imdbsearch.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imdbsearch.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainFragment extends Fragment {

    @BindView(R.id.title)
    public TextView title_textView;

    @BindView(R.id.movieName_editText)
    public EditText movieName_editText;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String str = "Easy search <font style='color:#DBA506'>movies</font>";
        title_textView.setText(Html.fromHtml(str));
    }


    @OnClick(R.id.search_btn)
    void searchClicked() {
        String movieName = movieName_editText.getText().toString().trim();
        if (movieName.length() != 0) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new ResultFragment(movieName))
                    .commit();
        } else {
            Toast.makeText(getContext(), "Please, enter a movie name", Toast.LENGTH_SHORT).show();
        }
    }
}
