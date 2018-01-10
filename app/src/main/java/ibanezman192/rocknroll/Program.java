package ibanezman192.rocknroll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Program extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

}
