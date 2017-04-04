package ibanezman192.rocknroll;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class About extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));

        initView();
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

    public void initView() {
        CardView card_about_2 = (CardView) findViewById(R.id.card_about_2);
        LinearLayout ll_card_about_2_shop = (LinearLayout) findViewById(R.id.ll_card_about_2_shop);
        LinearLayout ll_card_about_2_email = (LinearLayout) findViewById(R.id.ll_card_about_2_email);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_about_card_show);
        card_about_2.startAnimation(animation);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(300);
        alphaAnimation.setStartOffset(1000);

        ll_card_about_2_shop.setOnClickListener(this);
        ll_card_about_2_email.setOnClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_about_this);
        fab.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent();

        switch (view.getId()) {
            case R.id.ll_card_about_2_shop:
                intent.setData(Uri.parse(Constant.APP_URL));
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
                break;

            case R.id.ll_card_about_2_email:
                intent.setAction(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse(Constant.EMAIL));
                intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.about_email_intent));
                try {
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(About.this, getString(R.string.about_not_found_email), Toast.LENGTH_SHORT).show();
                }
                break;


            case R.id.fab_about_this:
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, Constant.SHARE_CONTENT);
                intent.setType("text/plain");
                startActivity(intent);
                break;
        }
    }
}
