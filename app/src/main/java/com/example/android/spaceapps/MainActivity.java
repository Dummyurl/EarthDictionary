package com.example.android.spaceapps;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.android.spaceapps.SearchWord.SearchWordFragement;
import com.example.android.spaceapps.ShowUserHistory.HistoryFragment;

public class MainActivity extends FragmentActivity {

    private TextView mTextMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                //TODO: Execute the loader using "new QueryLoader.execute()"

                case R.id.navigation_home:
                    android.app.FragmentManager fragmentManager1 = getFragmentManager();
                    android.app.FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                    SearchWordFragement newFragment1 = new SearchWordFragement();
                    fragmentTransaction1 = fragmentTransaction1.replace(R.id.fragment_container, newFragment1);
                    fragmentTransaction1.addToBackStack(null);
                    fragmentTransaction1.commit();
                    return true;

                case R.id.navigation_dashboard:
                    android.app.FragmentManager fragmentManager = getFragmentManager();
                    android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    HistoryFragment newFragment = new HistoryFragment();
                    fragmentTransaction = fragmentTransaction.replace(R.id.fragment_container, newFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    return true;

                case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.title_notifications);
                    return true;

                case R.id.navigation_notification:
                    //mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

}
