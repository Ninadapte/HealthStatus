package com.project.healthstatus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.project.healthstatus.ui.gather_insights.graphdata;
import com.project.healthstatus.ui.gather_insights.rawdata;
import com.project.healthstatus.ui.home.HomeFragment;

public class display_summery extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_summery);
        bottomNavigationView = findViewById(R.id.summery_bottomnavbar);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.summery_container,new rawdata()).commit();

    }



    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch(item.getItemId())
            {
                case R.id.nav_raw:
                    selectedFragment = new rawdata();
                    break;
                case R.id.nav_graph:
                    selectedFragment = new graphdata();
                    break;
                default:
                    selectedFragment = new rawdata();

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.summery_container , selectedFragment).commit();
            return true;
        }
    };
}
