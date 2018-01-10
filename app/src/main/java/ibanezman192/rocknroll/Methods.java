package ibanezman192.rocknroll;

import android.content.Context;
import android.database.SQLException;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by John on 4/1/2018.
 */

public class Methods {

    public static ArrayList<Producer> getProducers(Context context){

        ArrayList<Producer> producers = new ArrayList<Producer>();

        DataBaseHelper myDbHelperer;
        myDbHelperer = new DataBaseHelper(context, "RockDB");


        try {
            myDbHelperer.createDataBase();
        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }

        //// Opening Database the Instance that creates and accessing Menu Data
        try {
            myDbHelperer.openDataBase();
            producers = myDbHelperer.getProducers();

        } catch (SQLException sqle) {

            throw sqle;
        }

        //// Closing the Database to prevent memory Leakage
        try {
            myDbHelperer.close();
        } catch (Exception e) {
            Log.e("DATABASE STATE", "NOT_CLOSED");
        }


        return producers;
    }
}
