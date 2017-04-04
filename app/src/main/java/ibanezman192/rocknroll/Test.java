package ibanezman192.rocknroll;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.jetradar.desertplaceholder.DesertPlaceholder;


public class Test extends MainMenu {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        DesertPlaceholder desertPlaceholder = (DesertPlaceholder) findViewById(R.id.placeholder);
        desertPlaceholder.setOnButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Test.this, "Button clicked", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
