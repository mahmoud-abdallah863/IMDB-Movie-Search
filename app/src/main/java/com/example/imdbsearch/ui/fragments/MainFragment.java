package com.example.imdbsearch.ui.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imdbsearch.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainFragment extends Fragment {

    @BindView(R.id.cinemaImage)
    public ImageView cinemaImage;

    @BindView(R.id.title)
    public TextView title_textView;

    @BindView(R.id.internetError)
    public TextView internetError;

    @BindView(R.id.movieName_editText)
    public EditText movieName_editText;

    @BindView(R.id.search_btn)
    public Button search_btn;

    private Animation animation, cinemaAnimation;

    private boolean connected = false;
    private boolean firstTime = true;

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

        if (firstTime) {
            startCinemaAnimation();
            setTitle();
        } else {
            connected = true;
            setData();
        }
    }

    private void checkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        connected = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
        setData();
    }

    private void setData() {
        if (connected) {
            cinemaImage.setVisibility(View.GONE);
            internetError.setVisibility(View.GONE);
            movieName_editText.setVisibility(View.VISIBLE);
            search_btn.setVisibility(View.VISIBLE);
            setTitle();
            startAnimation();
        } else {
            internetError.setText("You are not connected to the internet...\nPlease connect and re-start the app");
        }
    }

    private void setTitle() {
        String str = "Easy search ";
        String word = "<font color='#DBA506'>movies</font>";
        title_textView.setText(Html.fromHtml(str + word));
    }

    private void startCinemaAnimation() {
        cinemaAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.cinema_image_animation);
        cinemaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                checkConnection();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        cinemaImage.setAnimation(cinemaAnimation);
    }

    private void startAnimation() {
        animation = AnimationUtils.loadAnimation(getContext(), R.anim.intro_animation);

        movieName_editText.setAnimation(animation);
        search_btn.setAnimation(animation);
    }


    @OnClick(R.id.search_btn)
    void searchClicked() {
        String movieName = movieName_editText.getText().toString().trim();
        if (movieName.length() != 0) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new ResultFragment(movieName))
                    .addToBackStack("result")
                    .commit();
            movieName_editText.setText("");
            firstTime = false;
        } else {
            Toast.makeText(getContext(), "Please, enter a movie name", Toast.LENGTH_SHORT).show();
        }
    }
}
