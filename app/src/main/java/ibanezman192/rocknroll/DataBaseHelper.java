package ibanezman192.rocknroll;


import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@SuppressLint("SdCardPath")
public class DataBaseHelper extends SQLiteOpenHelper {

    // The Android's default system path of your application database.
    private static String DB_PATH = "/data/data/ibanezman192.rocknroll/databases/";

    private static String DB_NAME = "RockDB";

    private SQLiteDatabase myDataBase;

    private final Context myContext;

    public static void SetDatabaseLanguage(String name) {
        DB_NAME = name;
    }

    private static final int DATABASE_VERSION = 8;

    /**
     * Constructor Takes and keeps a reference of the passed context in order to
     * access to the application assets and resources.
     *
     * @param context
     */
    public DataBaseHelper(Context context, String dbName) {
        super(context, dbName, null, DATABASE_VERSION);
        DB_NAME = dbName;

        this.myContext = context;
    }

    /**
     * Creates a empty database on the system and rewrites it with your own
     * database.
     */
    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if (dbExist) {
            // do nothing - database already exist
            try {
                this.getWritableDatabase();
            } catch (Exception e) {

            }
        } else {

            // By calling this method and empty database will be created into
            // the default system path
            // of your application so we are gonna be able to overwrite that
            // database with our database.
            try {
                this.getReadableDatabase();
            } catch (Exception e) {

            }
            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each
     * time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase() {

        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READONLY);

        } catch (SQLiteException e) {

            // database does't exist yet.

        }

        if (checkDB != null) {

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created
     * empty database in the system folder, from where it can be accessed and
     * handled. This is done by transfering bytestream.
     */
    private void copyDataBase() throws IOException {

        // Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        // Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        // transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
        // Log.d("Test", "in onUpgrade. Old is: " + myDataBase.getVersion());

        SQLiteDatabase checkdb = null;

        try {
            String myPath = DB_PATH + DB_NAME;
            checkdb = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READWRITE);

            // Once the DB has been copied, set the new version
            checkdb.setVersion(DATABASE_VERSION);
        } catch (SQLiteException e) {
            // database doesnt exist yet.
        }

    }

    public void openDataBase() throws SQLException {

        // Open the database
        String myPath = DB_PATH + DB_NAME;
        try {
            myDataBase = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READONLY);

        } catch (Exception e) {

        }

    }

    @Override
    public synchronized void close() {


        if (myDataBase != null)
            myDataBase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion < newVersion) {
            Log.v("Test", "Within onUpgrade. Old is: " + oldVersion
                    + " New is: " + newVersion);
            try {
                myContext.deleteDatabase(DB_NAME);
            } catch (Exception e) {

            }
            try {
                copyDataBase();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public ArrayList<Producer> getProducers() {
        ArrayList<Producer> producers = new ArrayList<>();

        try {
            Cursor c = myDataBase.rawQuery("SELECT * FROM Producer", null);
            int Column1 = c.getColumnIndex("_id");
            int Column2 = c.getColumnIndex("name");
            int Column3 = c.getColumnIndex("show");
            int Column4 = c.getColumnIndex("showHours");
            int Column5 = c.getColumnIndex("bio");
            int Column6 = c.getColumnIndex("imageId");
            c.moveToFirst();
            if (c != null) {
                do {
                    int id = c.getInt(Column1);
                    String name = c.getString(Column2);
                    String show = c.getString(Column3);
                    String showHours = c.getString(Column4);
                    String bio = c.getString(Column5);
                    String imageId = c.getString(Column6);

                    Producer tempProducer = new Producer(id, name, show, showHours, bio, imageId);
                    producers.add(tempProducer);
                } while (c.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myDataBase.close();
        }

        return producers;

    }

}