package com.mshlab.quickaid;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mshlab.quickaid.Fragment.PlacesFragment;
import com.mshlab.quickaid.Fragment.WebFragment;
import com.mshlab.quickaid.Obj.ResponeSearchPlcaes;
import com.mshlab.quickaid.Uitls.App;
import com.mshlab.quickaid.Uitls.ServerProtocol;

import java.util.ArrayList;
import java.util.List;

import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fm;

    ArrayList<ResponeSearchPlcaes.VenuesEntity> venuesEntityArrayList = new ArrayList<>();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    changeFragment(R.layout.fragment_places, null);
                    return true;
                case R.id.navigation_dashboard:
                    changeFragment(R.layout.fragment_webview, null);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        changeFragment(R.layout.fragment_places, null);


    }


    public boolean changeFragment(int fragmentId, Bundle bundle) {
        Fragment fragment;
        switch (fragmentId) {
            case R.layout.fragment_places:
                fragment = new PlacesFragment();
                break;
            case R.layout.fragment_webview:
                fragment = new WebFragment();
                break;


            default:
                return false;
        }


        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frameLayout, fragment).addToBackStack(String.valueOf(fragmentId)).commit();
        return true;
    }


}
