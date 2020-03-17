package com.project.rentaway;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.provider.SyncStateContract;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;

import static com.project.rentaway.R.id.emailUser;
import static com.project.rentaway.R.id.nav_view;
import static com.project.rentaway.R.id.screen;

public class navigationDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "navigationDrawer";
    Fragment fragment = null;
    NavigationView navigationView;
    Toolbar toolbar=null;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        firebaseAuth=FirebaseAuth.getInstance();
        user=firebaseAuth.getCurrentUser();
        View headerView = navigationView.getHeaderView(0);
        TextView email = (TextView) headerView.findViewById(emailUser);
        String user_email=user.getEmail();
        email.setText(user_email);
        if(savedInstanceState==null)
        {
            fragment = new Home();
            getSupportFragmentManager().beginTransaction().replace(screen,fragment).commit();
            getSupportActionBar().setTitle("Home");
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Log.d(TAG, "onBackPressed: "+fragment);
            if(!(fragment instanceof Home))
            {
                fragment = new Home();
                getSupportFragmentManager().beginTransaction().replace(screen,fragment).commit();
                getSupportActionBar().setTitle("Home");
            }
            else
            {
                finishAffinity();
                finish();
            }
        }
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

        return super.onOptionsItemSelected(item);
    }

   /* public void onResume(){
        super.onResume();
        fragment=new Home();
    }*/

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();
        if (id == R.id.settings) {
            fragment = new Settings();
            getSupportActionBar().setTitle("Settings");
        }
        else if (id == R.id.log_out) {
          firebaseAuth=FirebaseAuth.getInstance();
          if(firebaseAuth.getCurrentUser()==null)
          {
              finish();
              startActivity(new Intent(navigationDrawer.this,MainActivity.class));
          }
           FirebaseUser user =firebaseAuth.getCurrentUser();
           firebaseAuth.signOut();
           finish();
           startActivity(new Intent(navigationDrawer.this,MainActivity.class));
        } else if (id == R.id.favourites) {
            fragment = new Favourites();
            getSupportActionBar().setTitle("Favourites");

        } else if (id == R.id.contacted_properties) {
            fragment = new MyProperties();
            getSupportActionBar().setTitle("My Property");

        } else if (id == R.id.my_home) {
            fragment = new Home();
            getSupportActionBar().setTitle("Home");
        }
        else if(id == R.id.rent_property)
        {
            fragment=new RentMyProperty();
            getSupportActionBar().setTitle("Rent My Property");
        }
        if(fragment!=null){
            FragmentManager fragmentManager= getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.screen,fragment);
            fragmentTransaction.commit();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

