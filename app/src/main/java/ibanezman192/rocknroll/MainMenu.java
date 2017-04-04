package ibanezman192.rocknroll;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.LinkAddress;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.ShareActionProvider;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.jetradar.desertplaceholder.DesertPlaceholder;

public class MainMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Switch aSwitch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //An den exei internet enhmerwse xristi
        if (!isOnline()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.isonline)
                    .setTitle(R.string.unabletoconnect)
                    .setCancelable(false)
                    .setPositiveButton(R.string.settings,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent(Settings.ACTION_SETTINGS);
                                    finish();
                                    startActivity(intent);
                                }
                            }
                    )
                    .setNegativeButton(R.string.exit,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    finish();
                                }
                            }
                    );
            AlertDialog alert = builder.create();
            alert.show();
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Intent intent = new Intent();

        int id = item.getItemId();

        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_program) {

        } else if (id == R.id.nav_producers) {

        } else if (id == R.id.nav_contact) {

        } else if (id == R.id.nav_settings) {
            new Test();
        } else if (id == R.id.nav_info) {
//            composeEmail(new String[]{"giannisarmamentos@gmail.com"}, "1055 Rock");
            intent.setClass(this, About.class);
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            share();
        } else if (id == R.id.nav_exit) {
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Elegxei ean yparxei prosvasi sto internet
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    //Gia share pls
    private void share() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share));
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    // gia sxetika
    public void composeEmail(String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:giannisarmamentos@gmail.com")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

/*    // gia settings
    public void onSwitchClick(View v) {
        if (aSwitch.isChecked()) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark2));
            // getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary2));


            Toast.makeText(this, "Toggle ON", Toast.LENGTH_SHORT).show();
        } else {
            //getWindow().setStatusBarColor();
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
            Toast.makeText(this, "Toggle OFF", Toast.LENGTH_SHORT).show();
        }

    }*/


    //gia rate
    /*Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
    Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
    // To count with Play market backstack, After pressing back button,
    // to taken back to our application, we need to add following flags to intent.
    goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
    Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
    try {
        startActivity(goToMarket);
    } catch (ActivityNotFoundException e) {
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
    }*/

}
