package com.example.customlistview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomArrayAdapter extends ArrayAdapter<City>{

    private Activity activity;
    private City[] cities;

    public CustomArrayAdapter(Activity activity, City[] cities) {
        super(activity, R.layout.item, cities);
        this.activity = activity;
        this.cities = cities;

    }

    static class ViewContainer{
        ImageView imgCityLogo;
        TextView lblCityName;
        TextView lblCitySlogan;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewContainer viewContainer = null;
        View rowView = convertView;
        if(rowView == null){
            LayoutInflater inflater = activity.getLayoutInflater();
            rowView = inflater.inflate(R.layout.item, parent, false);
            viewContainer = new ViewContainer();
            viewContainer.lblCityName = (TextView) rowView.findViewById(R.id.lblCityName);
            viewContainer.lblCitySlogan = (TextView) rowView.findViewById(R.id.lblCitySlogan);
            viewContainer.imgCityLogo = (ImageView) rowView.findViewById(R.id.imgCityLogo);
            rowView.setTag(viewContainer);
        }else {
            viewContainer = (ViewContainer)rowView.getTag();
        }
        viewContainer.lblCityName.setText(cities[position].getCityName());
        viewContainer.lblCitySlogan.setText(cities[position].getSlogan());
        viewContainer.imgCityLogo.setImageResource(cities[position].getCityImage());

        return rowView;
    }
}
