package com.example.dell.sqllightdemo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.sqllightdemo.Activity.Appinfo;
import com.example.dell.sqllightdemo.R;

import java.util.ArrayList;

/**
 * Created by Tej710 on 06-01-2017.
 */

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.AppViewHolder> {
    ArrayList<Appinfo> Applist;
    Context context;
    Clickable clickable;

    public AppAdapter(ArrayList<Appinfo> applist, Context context,Clickable clickable) {
        Applist = applist;
        this.context = context;
        this.clickable = clickable;
    }
    public interface Clickable{
        void Click( int position);

    }
    @Override
    public AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_applicatioon,parent,false);

        return new AppViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AppViewHolder holder, final int position) {
        Appinfo application = Applist.get(position);
//        holder.tvId.setText(application.getID());
        holder.tvAppName.setText(application.getAPP_NAME());
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                clickable.Click(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Applist.size();
    }

    public class AppViewHolder extends RecyclerView.ViewHolder {
        TextView tvId,tvAppName;
        public AppViewHolder(View itemView) {
            super(itemView);
           // tvId = (TextView)itemView.findViewById(R.id.tvId);
            tvAppName = (TextView)itemView.findViewById((R.id.tvAppName));

        }



    }
}
