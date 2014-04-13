package com.ieee.imdbupdates;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
         
        TextView series = (TextView) rowView.findViewById(R.id.series);
        TextView epi_name = (TextView) rowView.findViewById(R.id.epiname);
        TextView epi_info = (TextView) rowView.findViewById(R.id.epinfo);
        TextView time = (TextView) rowView.findViewById(R.id.date);
        ImageView img = (ImageView) rowView.findViewById(R.id.image);
        series.setText(todoList.get(position).getText2());
        String epi = todoList.get(position).getText2().toString();
      //  String epis = todoList.get(position).getepi_info().toString();
//        if(epis!=null)
//        {
//        int a = epis.length();
//        int i;
//        for(i =0;i<=a;i++){
//        if(	epis.charAt(i)=='.')
//        	break;
//        }
//        String season = epis.substring(0, i);
//        String epiode = epis.substring(i, a);
//        epi_info.setText("Season:" + season + "    Episode:" + epiode );
//        }
        epi_name.setText("Next Episode:"+ todoList.get(position).getText1());
        
        time.setText("Releasing on:"+todoList.get(position).getTime());
        if(epi.equals("Dexter"))
        {
        	img.setImageResource(R.drawable.dexter);
        }
        else
        	if(epi.equals("Burn Notice"))
        {
        		img.setImageResource(R.drawable.burnnotice);
        }
        	 else
             	if(epi.equals("Bones"))
             {
             		img.setImageResource(R.drawable.bones);
             }
             
             	 else
                 	if(epi.equals("Suits"))
                 {
                 		img.setImageResource(R.drawable.suits);
                 }
                 
                 	 else
                     	if(epi.equals("Glee"))
                     {
                     		img.setImageResource(R.drawable.glee);
                     }
                     
                     	 else
                         	if(epi.equals("Castle"))
                         {
                         		img.setImageResource(R.drawable.castle);
                         }
                         
        
                         	 else
                             	if(epi.equals("Homeland"))
                             {
                             		img.setImageResource(R.drawable.homeland);
                             }
                             
                             	 else
                                 	if(epi.equals("The Vampire Diaries"))
                                 {
                                 		img.setImageResource(R.drawable.thevampirediaries);
                                 }
                                 
                                 	 else
                                     	if(epi.equals("The Big Bang Theory"))
                                     {
                                     		img.setImageResource(R.drawable.thebigbangtheory);
                                     }
                                     
                                     	 else
                                         	if(epi.equals("The Mentalist"))
                                         {
                                         		img.setImageResource(R.drawable.mntst);
                                         }
                                         	 else
                                              	if(epi.equals("The Breaking Bad"))
                                              {
                                              		img.setImageResource(R.drawable.bb);
                                              }
                                              
                                              	 else
                                                  	if(epi.equals("How I Met Your Mother"))
                                                  {
                                                  		img.setImageResource(R.drawable.howimetyourmother);
                                                  }
                                                  
                                                  	 else
                                                      	if(epi.equals("Grey's Anatomy"))
                                                      {
                                                      		img.setImageResource(R.drawable.greysnatomy);
                                                      }
                                                      
                                                      	 else
                                                          	if(epi.equals("Naruto Shippuden"))
                                                          {
                                                          		img.setImageResource(R.drawable.ns);
                                                          }
                                                          	else
                                                              	if(epi.equals("The Walking Dead"))
                                                              {
                                                              		img.setImageResource(R.drawable.dead);
                                                              }
        System.out.print("list adapter");
        return rowView;

	}
	
}

