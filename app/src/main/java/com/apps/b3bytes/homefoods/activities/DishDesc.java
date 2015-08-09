package com.apps.b3bytes.homefoods.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TextView;

import com.apps.b3bytes.homefoods.R;

public class DishDesc extends AppCompatActivity {
    private TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_desc);

        Toolbar toolBar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("Dish Desc");
        // enabling action bar app icon and behaving it as toggle button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        // create the TabHost that will contain the Tabs
        tabHost = (TabHost)findViewById(R.id.tabDishDesc);
        tabHost.setup();

        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec("Tab One");
        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("Tab Two");
        TabHost.TabSpec tabSpec3 = tabHost.newTabSpec("Tab Three");

        // Set the Tab name and the content
        tabSpec1.setIndicator("Prep");
        tabSpec1.setContent(R.id.tab1);


        tabSpec2.setIndicator("Ingredients");
        tabSpec2.setContent(R.id.tab2);


        tabSpec3.setIndicator("Nutrition");
        tabSpec3.setContent(R.id.tab3);

        // Add the tabs to the TabHost to display
        tabHost.addTab(tabSpec1);
        tabHost.addTab(tabSpec2);
        tabHost.addTab(tabSpec3);

        TextView x = (TextView) tabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
        x.setTextSize(11);
        x.setAllCaps(false);
        x = (TextView) tabHost.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
        x.setTextSize(11);
        x.setAllCaps(false);
        x = (TextView) tabHost.getTabWidget().getChildAt(2).findViewById(android.R.id.title);
        x.setTextSize(11);
        x.setAllCaps(false);

        //http://stackoverflow.com/questions/9826130/tabhost-inside-a-scrollview-forces-it-it-to-scroll-to-the-bottom
        //http://stackoverflow.com/questions/2014305/tabhost-inside-of-a-scrollview-always-scrolls-down-when-a-tab-is-clicked
        // Get the first component and make sure it is focusable. Note you have to use setFocusableInTouchMode and not setFocusable for this to work.
        TextView v = (TextView) findViewById(R.id.tvDishName);
        v.setFocusableInTouchMode(true);
        v.requestFocus();

        //Caused by: java.lang.OutOfMemoryError: Failed to allocate a 12549612 byte allocation with 6968540 free bytes and 6MB until OOM

        //LinearLayout row1 = (LinearLayout) findViewById(R.id.row1)
        //TextView text1 = row1.findViewById(R.id.text);

        //LinearLayout row2 = (LinearLayout) findViewById(R.id.row2)
        //TextView text2 = row2.findViewById(R.id.text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dish_desc, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
