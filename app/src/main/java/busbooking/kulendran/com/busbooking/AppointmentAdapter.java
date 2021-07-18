package busbooking.kulendran.com.busbooking;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AppointmentAdapter extends ArrayAdapter<Appointment> {
    List<Appointment> list;
    Context context;
    int resource;


    public AppointmentAdapter(@NonNull Context context, int resource, List<Appointment> list) {
        super(context, resource,list);

        this.context  = context;
        this.resource = resource;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(resource, null);

        TextView txt_from =  view.findViewById(R.id.txtfrom);
        TextView txt_to =   view.findViewById(R.id.txtto);
        TextView txt_date =   view.findViewById(R.id.txtDate);
        TextView txt_price = view.findViewById(R.id.txtprice);

        Appointment appointment = list.get(position);
        txt_from.setText(appointment.getBus_from());
        txt_to.setText(appointment.getBus_to());
        txt_date.setText("Depature Time  "+appointment.getBus_time());
        txt_price.setText("Ticket Price :"+appointment.getBus_Tprice()+"/=");

        return view;
    }
}
