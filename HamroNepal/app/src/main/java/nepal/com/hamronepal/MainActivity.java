package nepal.com.hamronepal;

import android.content.res.Configuration;
import android.graphics.Typeface;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private ListView drawerList;
    private DrawerLayout drawerLayout;
    private ArrayAdapter<String>arrayAdapter;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private String activityTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerList = (ListView)findViewById(R.id.navList);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/AnandaLipiBoldCnBt.ttf");
        TextView tv = (TextView)findViewById(R.id.HeaderText);
        tv.setTypeface(tf);


    }

    public void addDrawerItems(){
        String [] drawerItemsList = { "Today", "calender", "News"};
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,drawerItemsList);
        drawerList.setAdapter(arrayAdapter);

        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Thank you for choosing hamro nepal apps!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupDrawer(){
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close){
            /*Called when a drawer has settled in a completly open state*/
            public void onDrawerOpened(View drawerView){
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Menu");
                invalidateOptionsMenu();//created call to onPrepareOptionsMenu()
            }

            /*Called when a drawer has settled in a completly open state*/
            public void onDrawerClosed(View closedView){
                super.onDrawerClosed(closedView);
                getSupportActionBar().setTitle(activityTitle);
                invalidateOptionsMenu();//created call to onPrepareOptionsMenu()
            }
        };

        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        //sync the toggle start after onRestoreInstanceState has occured
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        //Activate the navigation drawer toggle
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
