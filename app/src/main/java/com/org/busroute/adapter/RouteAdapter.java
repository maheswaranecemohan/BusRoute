package com.org.busroute.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.org.busroute.R;
import com.org.busroute.model.Route;

import java.util.List;
/**
 * This  is RouteAdapter
 */
public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.MyViewHolder> {
    private Context mContext;
    private List<Route> busRoutesList;
    private RoutesAdapterListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        public TextView routeName;
        public ImageView iconProfile;
        public LinearLayout messageContainer;

        public MyViewHolder(View view) {
            super(view);
            routeName = (TextView) view.findViewById(R.id.routeName);
            iconProfile = (ImageView) view.findViewById(R.id.iconProfile);
            messageContainer = (LinearLayout) view.findViewById(R.id.message_container);
            view.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View view) {
            listener.onRouteRowClicked(getAdapterPosition());
            view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
            return true;
        }
    }

    public RouteAdapter(Context mContext, List<Route> busRoutesList, RoutesAdapterListener listener) {
        this.mContext = mContext;
        this.busRoutesList = busRoutesList;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_routes_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Route busRoute = busRoutesList.get(position);
        holder.routeName.setText(busRoute.getName());
        Glide.with(mContext).load(busRoute.getImage())
                .placeholder(R.drawable.ic_no_image)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.iconProfile);
        applyClickEvents(holder, position);
    }

    /*Click event for RecyclerView item*/
    private void applyClickEvents(MyViewHolder holder, final int position) {
        holder.messageContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRouteRowClicked(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return busRoutesList.size();
    }

    public interface RoutesAdapterListener {
        void onRouteRowClicked(int position);
    }
}