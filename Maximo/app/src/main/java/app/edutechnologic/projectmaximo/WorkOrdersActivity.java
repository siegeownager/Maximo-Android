package app.edutechnologic.projectmaximo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WorkOrdersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_orders);

        // Gets the data repository in write mode
        WorkOrderDbHelper DbHelper = new WorkOrderDbHelper(getApplicationContext());
        SQLiteDatabase dbWriteable = DbHelper.getWritableDatabase();

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
        final Button homeButton = findViewById(R.id.home_nav_btn);
        homeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BottomMenuBar.menuClick(v);
            }
        });
    }
}
