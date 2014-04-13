package com.ieee.imdbupdates;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class ListAdapter extends ArrayAdapter<Todo> {
    private final Context context;
    private final List<Todo> todoList;
	public ListAdapter(Context context, List<Todo> todoList) {
		super(context, R.layout.date_series, todoList);
		this.context = context;
		this.todoList = todoList;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		 
        View rowView = inflater.inflate(R.layout.date_series, parent, false);
         
    //    TextView series = (TextView) rowView.findViewById(R.id.series);
        TextView epi_name = (TextView) rowView.findViewById(R.id.epiname);
        TextView epi_info = (TextView) rowView.findViewById(R.id.epinfo);
        TextView time = (TextView) rowView.findViewById(R.id.date);
     //   series.setText(todoList.get(position).getText2());
        epi_info.setText(todoList.get(position).getepi_info());
        epi_name.setText(todoList.get(position).getText1());
        time.setText(todoList.get(position).getTime());
        System.out.print("list adapter");
        return rowView;

	}
	
}

