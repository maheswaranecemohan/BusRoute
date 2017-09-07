package com.org.busroute.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.org.busroute.R;
import com.org.busroute.model.Stop;

import java.util.List;
/**
 * This  is StopAdapter
 */
public class StopAdapter extends RecyclerView.Adapter<StopAdapter.MyViewHolder> {
    private Context mContext;
    private List<Stop> stopList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView routeName;
        public ImageView iconProfile,imgVerLine;
        public LinearLayout messageContainer;


        public MyViewHolder(View view) {
            super(view);
            routeName = (TextView) view.findViewById(R.id.routeName);
            iconProfile = (ImageView) view.findViewById(R.id.iconProfile);
            imgVerLine = (ImageView) view.findViewById(R.id.img_ver_line);
            messageContainer = (LinearLayout) view.findViewById(R.id.message_container);

        }
    }

    public StopAdapter(Context mContext, List<Stop> stopList) {
        this.mContext = mContext;
        this.stopList = stopList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_stops_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Stop stop = stopList.get(position);
        holder.routeName.setText(stop.getName());

        if(position==(stopList.size()-1)) {
            holder.imgVerLine.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    public int getItemCount() {
        return stopList.size();
    }


}