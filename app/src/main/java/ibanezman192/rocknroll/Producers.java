package ibanezman192.rocknroll;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Producers extends AppCompatActivity {

    ListView lvProds;

    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producers);

        lvProds = (ListView) findViewById(R.id.lvProds);

        final ArrayList<Producer> producers = Methods.getProducers(getApplicationContext());

        Producer[] array = producers.toArray(new Producer[producers.size()]);

        final CustomListAdapter adapter = new CustomListAdapter(this, array);

        lvProds.setAdapter(adapter);

        lvProds.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                try{
                    Producer producer = producers.get(position);

                    displayAlertDialog(producer,context);


                }catch (Exception e){

                }

            }
        });

    }

    private void displayAlertDialog(Producer producer, Context context){
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        builder.setTitle("Bio")
                .setMessage(producer.getBio())
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setIcon(R.drawable.ic_people_black_24dp)
                .show();
    }
}
