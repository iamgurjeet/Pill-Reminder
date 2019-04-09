package com.o7services.pillreminder;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import com.o7services.pillreminder.adapters.ViewPagerAdapter;
import com.o7services.pillreminder.fragments.HistoryFragment;
import com.o7services.pillreminder.fragments.TodayFragment;
import com.o7services.pillreminder.fragments.TomorrowFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private int[] tabIcons = {
            android.R.drawable.picture_frame,
            android.R.drawable.ic_lock_idle_alarm,
            android.R.drawable.ic_menu_call
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    private void setupViewPager(ViewPager viewPager) {


        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HistoryFragment());
        adapter.addFragment(new TodayFragment());
        adapter.addFragment(new TomorrowFragment());
        viewPager.setAdapter(adapter);
    }

    private void setupTabIcons() {

        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();


        String todayString = new SimpleDateFormat("EEE, MMM d").format(today);
        String tomorrowString = new SimpleDateFormat("EEE, MMM d").format(tomorrow);


        TextView tt1 = new TextView(this);
        tt1.setText(Html.fromHtml("<b>HISTORY</b>"));
        tt1.setTextColor(Color.WHITE);
        tt1.setGravity(Gravity.CENTER);
        tt1.setHeight(200);

        TextView tt2 = new TextView(this);
        tt2.setText(Html.fromHtml("<b>TODAY</b><br><small>" + todayString + "</small>"));
        tt2.setTextColor(Color.WHITE);
        tt2.setGravity(Gravity.CENTER);
        tt2.setHeight(200);

        TextView tt3 = new TextView(this);
        tt3.setText(Html.fromHtml("<b>TOMORROW</b><br><small>" + tomorrowString + "</small>"));
        tt3.setTextColor(Color.WHITE);
        tt3.setGravity(Gravity.CENTER);
        tt3.setHeight(200);

        tabLayout.getTabAt(0).setCustomView(tt1);
        tabLayout.getTabAt(1).setCustomView(tt2);
        tabLayout.getTabAt(2).setCustomView(tt3);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
/**
 * Handle action bar item clicks here. The action bar will
 * automatically handle clicks on the Home/Up button, so long
 * as you specify a parent activity in AndroidManifest.xml.
 */
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, ScheduleActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        if (id == R.id.action_add) {
            Intent intent = new Intent(this, AddActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        if (id == R.id.action_edit) {
            Intent intent = new Intent(this, PillBoxActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }


}
