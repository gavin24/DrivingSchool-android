package com.example.gavin.drivingschool;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gavin.drivingschool.Domain.Appointment;
import com.example.gavin.drivingschool.Domain.UserImage;

import java.util.ArrayList;

/**
 * Created by gavin on 2017/06/27.
 */
public class AppointmentListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final ArrayList<String> itemname;


    public AppointmentListAdapter(Activity context, ArrayList<String> itemname) {
        super(context, R.layout.alternatelist, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.alternatelist, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);



        txtTitle.setText(itemname.get(position));




        return rowView;

    }
}
