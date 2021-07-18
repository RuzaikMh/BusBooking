package busbooking.kulendran.com.busbooking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class BusLists extends AppCompatActivity {

    SearchView searchView;
    java.util.List<Appointment> List;
    List<Appointment> resycleList;
    ListView listView;
    public static String SELECTID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_lists);

        listView = findViewById(R.id.list_search);
        searchView = findViewById(R.id.search_view);

        DatabaseHelper db = new DatabaseHelper(this);
        resycleList = new ArrayList<Appointment>();
        List = db.getAllAppointments();

        for (Appointment app : List) {
            resycleList.add(app);
        }
        AppointmentAdapter adapter = new AppointmentAdapter(this,
                R.layout.list_layout_bus_list, resycleList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Appointment appointment = resycleList.get(i);
                SELECTID = appointment.getId().toString();

                Intent intent = new Intent(getApplicationContext(),BookBusActivity.class);
                startActivity(intent);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                getSearchResult(s);
                return false;
            }
        });
    }

    private void getSearchResult(String search) {
        DatabaseHelper db = new DatabaseHelper(this);
        resycleList = new ArrayList<Appointment>();
        List = db.getAllAppointments();

        for (Appointment app : List) {
            if (app.getBus_from().contains(search) || app.getBus_to().contains(search)) {
                resycleList.add(app);
            }
        }
        AppointmentAdapter adapter = new AppointmentAdapter(this,
                R.layout.list_layout_bus_list, resycleList);
        listView.setAdapter(adapter);

    }
}
