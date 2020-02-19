package com.mshlab.quickaid;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.model.SliderPage;
import com.mshlab.quickaid.Uitls.App;
import com.mshlab.quickaid.Uitls.TinyDB;

public class IntroActivity extends AppIntro {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SliderPage sliderPage = new SliderPage();
        sliderPage.setTitle("Welcome to Quick Aid");
        sliderPage.setDescription("Here you can find a list of the nearest hospitals around");
        sliderPage.setImageDrawable(R.drawable.icon_hospital);
        sliderPage.setBgColor(getApplicationContext().getResources().getColor(R.color.colorAccent));

        SliderPage sliderPage2 = new SliderPage();
        sliderPage2.setTitle("First Aid");
        sliderPage2.setDescription("if you want to train and learn how to do the first aid\n" +
                "By videos made by National Safety Council");
        sliderPage2.setImageDrawable(R.drawable.learn);
        sliderPage2.setBgColor(getApplicationContext().getResources().getColor(R.color.colorAccent));






        SliderPage sliderPage3 = new SliderPage();
        sliderPage3.setTitle("Permissions");
        sliderPage3.setDescription("we need location to find the hospital near you");
        sliderPage3.setImageDrawable(R.drawable.pin_loc);
        sliderPage3.setBgColor(getApplicationContext().getResources().getColor(R.color.colorAccent));



        askForPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, 3); // OR


        addSlide(AppIntroFragment.newInstance(sliderPage));
        addSlide(AppIntroFragment.newInstance(sliderPage2));
        addSlide(AppIntroFragment.newInstance(sliderPage3));





        setFadeAnimation();

        // Hide Skip/Done button.
        showSkipButton(true);
        setBackButtonVisibilityWithDone(true);
        setProgressButtonEnabled(true);


    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        new TinyDB(App.mContext).putBoolean("userDoneIntro", true);
        Intent intent = new Intent(IntroActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }
}
