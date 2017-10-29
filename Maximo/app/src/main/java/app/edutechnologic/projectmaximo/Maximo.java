package app.edutechnologic.projectmaximo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// import static app.edutechnologic.projectmaximo.DashboardSettingsActivity.changes;

public class Maximo extends AppCompatActivity {
    public static String userIdentity = "";
    public static ArrayList<WorkItem> workitem_list;
    public ArrayList<String> previous_settings = new ArrayList<String>();

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);

        //Initialize chat bot variables
        MaximoUtility.initialize();

        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getApplicationContext());
        SensorGaugeReaderDbHelper sDbHelper = new SensorGaugeReaderDbHelper(getApplicationContext());
        WorkItemsDbHelper wDbHelper = new WorkItemsDbHelper(getApplicationContext());

        // Gets the data repository in write mode
        SQLiteDatabase dbWriteable = mDbHelper.getWritableDatabase();
        SQLiteDatabase sdbWriteable = sDbHelper.getWritableDatabase();
        SQLiteDatabase wdbWriteable = wDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_USERID, "1");
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_USERNAME, "userone");
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_FIRSTNAME, "Mike");
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_LASTNAME, "Rowsoft");

        // Insert the new row, returning the primary key value of the new row
        long newRowId = dbWriteable.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);

        //Sensor Gauge Sensor 1
        values = new ContentValues();
        values.put(SensorGaugeReaderContract.FeedEntry.COLUMN_NAME_SENSORID, "1");
        values.put(SensorGaugeReaderContract.FeedEntry.COLUMN_NAME_SENSORNAME, "Sensor 1");
        values.put(SensorGaugeReaderContract.FeedEntry.COLUMN_NAME_SENSORTOTALVALUE, "500");
        values.put(SensorGaugeReaderContract.FeedEntry.COLUMN_NAME_SENSORACTUALVALUE, "325");

        newRowId = sdbWriteable.insert(SensorGaugeReaderContract.FeedEntry.TABLE_NAME, null, values);


        //Sensor Gauge Sensor 2
        values = new ContentValues();
        values.put(SensorGaugeReaderContract.FeedEntry.COLUMN_NAME_SENSORID, "2");
        values.put(SensorGaugeReaderContract.FeedEntry.COLUMN_NAME_SENSORNAME, "Sensor 2");
        values.put(SensorGaugeReaderContract.FeedEntry.COLUMN_NAME_SENSORTOTALVALUE, "700");
        values.put(SensorGaugeReaderContract.FeedEntry.COLUMN_NAME_SENSORACTUALVALUE, "405");

        newRowId = sdbWriteable.insert(SensorGaugeReaderContract.FeedEntry.TABLE_NAME, null, values);

        //delete all items in workitem table
        wdbWriteable.delete(WorkItemsContract.WorkItemsEntry.TABLE_NAME, null, null);
        // Work Items 1
        values = new ContentValues();
        values.put(WorkItemsContract.WorkItemsEntry.COLUMN_NAME_ITEM, "item1");
        newRowId = wdbWriteable.insert(WorkItemsContract.WorkItemsEntry.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(WorkItemsContract.WorkItemsEntry.COLUMN_NAME_ITEM, "item2");
        newRowId = wdbWriteable.insert(WorkItemsContract.WorkItemsEntry.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(WorkItemsContract.WorkItemsEntry.COLUMN_NAME_ITEM, "item3");
        newRowId = wdbWriteable.insert(WorkItemsContract.WorkItemsEntry.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(WorkItemsContract.WorkItemsEntry.COLUMN_NAME_ITEM, "item4");
        newRowId = wdbWriteable.insert(WorkItemsContract.WorkItemsEntry.TABLE_NAME, null, values);


        /**
         *
         * Get Readable versions of databases
         * */
        SQLiteDatabase dbReadable = mDbHelper.getReadableDatabase();
        SQLiteDatabase sdbReadable = sDbHelper.getReadableDatabase();
        SQLiteDatabase wdbReadable = wDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                FeedReaderContract.FeedEntry._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_USERID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_USERNAME,
                FeedReaderContract.FeedEntry.COLUMN_NAME_FIRSTNAME,
                FeedReaderContract.FeedEntry.COLUMN_NAME_LASTNAME
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_FIRSTNAME + " = ?";
        String[] selectionArgs = {"Mike"};

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                FeedReaderContract.FeedEntry.COLUMN_NAME_USERID + " ASC";

        Cursor cursor = dbReadable.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        // Read in the rows with the cursor
        List<String> itemIds = new ArrayList<String>();
        while (cursor.moveToNext()) {
            String itemId = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_FIRSTNAME));
            itemIds.add(itemId);
        }
        cursor.close();
        userIdentity = " " + itemIds.get(0);

        /**
         * read work items
         *
         * */
        cursor = wdbReadable.rawQuery("select * from " + WorkItemsContract.WorkItemsEntry.TABLE_NAME, null);

        workitem_list = new ArrayList<WorkItem>();
        while (cursor.moveToNext()) {
            String itemId = cursor.getString(
                    cursor.getColumnIndexOrThrow(WorkItemsContract.WorkItemsEntry.COLUMN_NAME_ITEM));
            workitem_list.add(new WorkItem(itemId));
        }

        cursor.close();


        // We're calling this last so the name can be pulled before
        // the screen is created

        setContentView(R.layout.activity_maximo);

        /**
         * set fragment's visibility based on the dashboard settings view
         */
        View inbox = findViewById(R.id.inbox);
        View bulletin_board = findViewById(R.id.bulletin_board);
        View gauge = findViewById(R.id.gauge);
        View status_window = findViewById(R.id.status_window);
        ArrayList<String> curr_settings = new ArrayList<String>();
        File file = new File(getFilesDir(), "dashboardSetting.txt");

        // file.delete();
        if(!file.exists()){
            inbox.setVisibility(View.VISIBLE);
            bulletin_board.setVisibility(View.VISIBLE);
            gauge.setVisibility(View.VISIBLE);
            status_window.setVisibility(View.VISIBLE);
        }else{
            // read the file
            try {
                InputStreamReader isr = new InputStreamReader(openFileInput("dashboardSetting.txt"));
                BufferedReader br = new BufferedReader(isr);

                String sLine = null;
                while ((sLine = br.readLine()) != null) {
                    curr_settings.add(sLine);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            if(curr_settings.contains("inbox")){
                inbox.setVisibility(View.VISIBLE);
            }else{
                inbox.setVisibility(View.GONE);
            }

            if(curr_settings.contains("bulletin_board")){
                bulletin_board.setVisibility(View.VISIBLE);
            }else{
                bulletin_board.setVisibility(View.GONE);
            }

            if(curr_settings.contains("gauge")){
                gauge.setVisibility(View.VISIBLE);
            }else{
                gauge.setVisibility(View.GONE);
            }

            if(curr_settings.contains("status_window")){
                status_window.setVisibility(View.VISIBLE);
            }else{
                status_window.setVisibility(View.GONE);
            }

        }


        //bottom navbar menu button functionality
        final Button mediaButton = findViewById(R.id.dashboard_nav_btn);
        mediaButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BottomMenuBar.menuClick(v);
            }
        });
        final Button chatButton = findViewById(R.id.chat_nav_btn);
        chatButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BottomMenuBar.menuClick(v);
            }
        });
        final Button dashboardSettingsButton = findViewById(R.id.dashboard_settings_btn);
        dashboardSettingsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BottomMenuBar.menuClick(v);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_maximo, menu);
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

        return super.onOptionsItemSelected(item);
    }
}
