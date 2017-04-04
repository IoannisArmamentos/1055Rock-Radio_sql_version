package ibanezman192.rocknroll;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.hide();

        new Timer().schedule(new TimerTask() {
            public void run() {
                startActivity(new Intent(Splash.this, MainMenu.class));
            }
        }, 2500 /*amount of time in milliseconds before execution*/);

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
