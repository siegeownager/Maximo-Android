package app.edutechnologic.projectmaximo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class WorkOrdersActivity extends AppCompatActivity {
    //class variables
    public static ArrayList<Integer> workOrderNumbers;
    public static ArrayList<String> workOrderDescriptions;
    public static ArrayList<Integer> workOrderAssetNumbers;
    public static ArrayList<String> workOrderLocations;
    public static ArrayList<String> workOrderReportedDates;
    public static ArrayList<String> workOrderStatuses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_orders);

        // Gets the data repository in write mode
        WorkOrderDbHelper DbHelper = new WorkOrderDbHelper(getApplicationContext());
        SQLiteDatabase dbWriteable = DbHelper.getWritableDatabase();

        // delete all items in workitem table
        // dbWriteable.delete(WorkOrderContract.WorkOrderEntry.TABLE_NAME, null, null);

        //Create a few work order entries

        //First Entry values
        ContentValues values = new ContentValues();
        values.put(WorkOrderContract.WorkOrderEntry.COLUMN_NAME_NUMBER, "1000");
        values.put(WorkOrderContract.WorkOrderEntry.COLUMN_NAME_DESCRIPTION, "Relocate Guard Rails Around Compressor");
        values.put(WorkOrderContract.WorkOrderEntry.COLUMN_NAME_ASSETNUMBER, "11300");
        values.put(WorkOrderContract.WorkOrderEntry.COLUMN_NAME_LOCATION, "BR300");
        values.put(WorkOrderContract.WorkOrderEntry.COLUMN_NAME_REPORTEDDATE, "10/10/17 1:14 PM");
        values.put(WorkOrderContract.WorkOrderEntry.COLUMN_NAME_STATUS, "APPR");
        // Insert the new row, returning the primary key value of the new row
        long newRowId = dbWriteable.insert(WorkOrderContract.WorkOrderEntry.TABLE_NAME, null, values);

        //Second Entry values
        values = new ContentValues();
        values.put(WorkOrderContract.WorkOrderEntry.COLUMN_NAME_NUMBER, "1007");
        values.put(WorkOrderContract.WorkOrderEntry.COLUMN_NAME_DESCRIPTION, "Packaging Mach. Elevator & Drainpan Inspection");
        values.put(WorkOrderContract.WorkOrderEntry.COLUMN_NAME_ASSETNUMBER, "13141");
        values.put(WorkOrderContract.WorkOrderEntry.COLUMN_NAME_LOCATION, "BPM3100");
        values.put(WorkOrderContract.WorkOrderEntry.COLUMN_NAME_REPORTEDDATE, "10/12/17 9:33 AM");
        values.put(WorkOrderContract.WorkOrderEntry.COLUMN_NAME_STATUS, "APPR");
        // Insert the new row, returning the primary key value of the new row
        newRowId = dbWriteable.insert(WorkOrderContract.WorkOrderEntry.TABLE_NAME, null, values);

        //Third Entry values
        values = new ContentValues();
        values.put(WorkOrderContract.WorkOrderEntry.COLUMN_NAME_NUMBER, "1214");
        values.put(WorkOrderContract.WorkOrderEntry.COLUMN_NAME_DESCRIPTION, "Quarterly and Annual Maintenance on Pump 11430");
        values.put(WorkOrderContract.WorkOrderEntry.COLUMN_NAME_ASSETNUMBER, "11430");
        values.put(WorkOrderContract.WorkOrderEntry.COLUMN_NAME_LOCATION, "BR430");
        values.put(WorkOrderContract.WorkOrderEntry.COLUMN_NAME_REPORTEDDATE, "10/25/17 4:50 PM");
        values.put(WorkOrderContract.WorkOrderEntry.COLUMN_NAME_STATUS, "APPR");
        // Insert the new row, returning the primary key value of the new row
        newRowId = dbWriteable.insert(WorkOrderContract.WorkOrderEntry.TABLE_NAME, null, values);

        //Get readable version of DB
        SQLiteDatabase dbReadable = DbHelper.getReadableDatabase();

        /*
         * Define a projection that specifies which columns from the database
         * you will actually use after this query.
         */
        String[] projection = {
                WorkOrderContract.WorkOrderEntry._ID,
                WorkOrderContract.WorkOrderEntry.COLUMN_NAME_NUMBER,
                WorkOrderContract.WorkOrderEntry.COLUMN_NAME_DESCRIPTION,
                WorkOrderContract.WorkOrderEntry.COLUMN_NAME_ASSETNUMBER,
                WorkOrderContract.WorkOrderEntry.COLUMN_NAME_LOCATION,
                WorkOrderContract.WorkOrderEntry.COLUMN_NAME_REPORTEDDATE,
                WorkOrderContract.WorkOrderEntry.COLUMN_NAME_STATUS
        };

        /*
        //Define cursor (getting all columns)
        Cursor cursor = dbReadable.rawQuery("select * from "+WorkOrderContract.WorkOrderEntry.TABLE_NAME, null);

        //Populate Array Lists with entries using the cursor
        workOrderNumbers = new ArrayList<> ();
        workOrderDescriptions = new ArrayList<> ();
        workOrderAssetNumbers = new ArrayList<> ();
        workOrderLocations = new ArrayList<> ();
        workOrderReportedDates = new ArrayList<> ();
        workOrderStatuses = new ArrayList<> ();
        while(cursor.moveToNext()) {

            //Add in work order number, description, asset number, location, reported date, and
            //status to the appropriate array list.
            Integer number = cursor.getInt(
                    cursor.getColumnIndexOrThrow(WorkOrderContract.WorkOrderEntry.COLUMN_NAME_NUMBER));
            workOrderNumbers.add(number);
            String description = cursor.getString(
                    cursor.getColumnIndexOrThrow(WorkOrderContract.WorkOrderEntry.COLUMN_NAME_DESCRIPTION));
            workOrderDescriptions.add(description);
            Integer assetNumber = cursor.getInt(
                    cursor.getColumnIndexOrThrow(WorkOrderContract.WorkOrderEntry.COLUMN_NAME_ASSETNUMBER));
            workOrderAssetNumbers.add(assetNumber);
            String location = cursor.getString(
                    cursor.getColumnIndexOrThrow(WorkOrderContract.WorkOrderEntry.COLUMN_NAME_LOCATION));
            workOrderLocations.add(location);
            String reportedDate = cursor.getString(
                    cursor.getColumnIndexOrThrow(WorkOrderContract.WorkOrderEntry.COLUMN_NAME_REPORTEDDATE));
            workOrderReportedDates.add(reportedDate);
            String status = cursor.getString(
                    cursor.getColumnIndexOrThrow(WorkOrderContract.WorkOrderEntry.COLUMN_NAME_STATUS));
            workOrderStatuses.add(status);
        }
        cursor.close();
        */

        //bottom navbar menu button functionality
        final Button chatButton = findViewById(R.id.chat_nav_btn);
        chatButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BottomMenuBar.menuClick(v);
            }
        });
        final Button homeButton = findViewById(R.id.home_nav_btn);
        homeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BottomMenuBar.menuClick(v);
            }
        });
    }
}