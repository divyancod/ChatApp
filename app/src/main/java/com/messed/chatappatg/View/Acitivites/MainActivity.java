package com.messed.chatappatg.View.Acitivites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.messed.chatappatg.R;
import com.messed.chatappatg.View.Adapters.TabPageAdapter;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;
    FirebaseAuth firebaseAuth;
    static final String TAG="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout=findViewById(R.id.tabs);
        viewPager=findViewById(R.id.viewpageid);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null)
        {
            finish();
            startActivity(new Intent(this,FirstScreen.class));
        }
        Log.e(TAG, "onCreate: "+firebaseAuth.getUid());
        toolbar=findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);
        //--------------------------Tabs declaration starts here---------------------------------------------------//
        TabLayout.Tab firstTab = tabLayout.newTab();
        firstTab.setText("User-Chat");
        tabLayout.addTab(firstTab);
        TabLayout.Tab secondTab = tabLayout.newTab();
        secondTab.setText("Group-Chat");
        tabLayout.addTab(secondTab);
        //------------------------------------------tab layouts-----------------------------------------------------//
        TabPageAdapter tabPageAdapter = new TabPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabPageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }

    private TabLayout.OnTabSelectedListener onTabSelectedListener(final ViewPager pager) {
        return new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        };
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        Log.e(TAG, "onOptionsItemSelected: "+"jdhfdf dhfdjfk,jdnfkhd fbgdfdgjfhgj" );
        switch (item.getItemId()) {
            case R.id.action_settings:
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(this,FirstScreen.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

}
