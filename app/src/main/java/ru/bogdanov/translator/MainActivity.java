package ru.bogdanov.translator;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import ru.bogdanov.translator.Fragments.LearnFragment;
import ru.bogdanov.translator.Fragments.ListFragment;
import ru.bogdanov.translator.Fragments.NewFragment;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    ViewPager viewPager;
    BottomNavigationView bottomNavigationView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_new:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_list:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_learn:
                    viewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewPager = findViewById(R.id.viewPager);

        setUpViewPager(viewPager);
    }

    void setUpViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new NewFragment());
        adapter.addFragment(new ListFragment());
        adapter.addFragment(new LearnFragment());

        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.navigation_new);
                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.navigation_list);
                        break;
                    case 2:
                        bottomNavigationView.setSelectedItemId(R.id.navigation_learn);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
