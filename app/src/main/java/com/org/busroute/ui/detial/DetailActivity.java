package com.org.busroute.ui.detial;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.org.busroute.R;
import com.org.busroute.adapter.StopAdapter;
import com.org.busroute.model.Route;
import com.org.busroute.model.Stop;
import java.util.ArrayList;
import java.util.List;
/**
 * This  is DetailActivity
 */
public class DetailActivity extends AppCompatActivity {
    private String TAG = DetailActivity.class.getName();
    private Context ctx;
    private RecyclerView recyclerView;
    private List<Stop> stoplist = new ArrayList<>();
    private StopAdapter stopAdapter;
    private ImageView imgProfile, imgAccessable;
    private TextView routeName, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Route route = getIntent().getParcelableExtra("route");
        ctx = DetailActivity.this;

        stopAdapter = new StopAdapter(this, stoplist);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        imgProfile = (ImageView) findViewById(R.id.img_profile);
        imgAccessable = (ImageView) findViewById(R.id.img_accessable);
        routeName = (TextView) findViewById(R.id.routeName);
        description = (TextView) findViewById(R.id.description);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(stopAdapter);

        if (route != null) {

            routeName.setText(route.getName());
            description.setText(route.getDescription());
            if (route.getAccessible().equals("true")) {
                imgAccessable.setVisibility(View.VISIBLE);
            }

            Glide.with(ctx).load(route.getImage())
                    .placeholder(R.drawable.ic_no_image)
                    .thumbnail(0.5f)
                    .crossFade()
                    .into(imgProfile);
            stoplist.addAll(route.getStops());
            stopAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
    }

}
