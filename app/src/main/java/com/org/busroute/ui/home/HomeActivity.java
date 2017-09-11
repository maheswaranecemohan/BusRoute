package com.org.busroute.ui.home;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.org.busroute.R;
import com.org.busroute.adapter.RouteAdapter;
import com.org.busroute.model.BusRoute;
import com.org.busroute.model.Route;
import com.org.busroute.ui.detail.DetailActivity;
import com.org.busroute.utils.Connectivity;
import com.org.busroute.utils.ToastUtils;
import java.util.ArrayList;
import java.util.List;
/**
 * This  is HomeActivity
 */
public class HomeActivity extends AppCompatActivity implements IHomeView, RouteAdapter.RoutesAdapterListener, SwipeRefreshLayout.OnRefreshListener {
    private String TAG = HomeActivity.class.getName();
    private Context ctx;
    private RecyclerView recyclerView;
    private List<Route> busRoutesList = new ArrayList<>();
    private RouteAdapter routeAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    public HomePresenter homePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ctx = HomeActivity.this;
        homePresenter = new HomePresenter(this);

        routeAdapter = new RouteAdapter(this, busRoutesList, this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(routeAdapter);

        //Start
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        getRoutes();
                                    }
                                }
        );
    }

    private void getRoutes() {
        /*check the internet or mobile data is available*/
        if (Connectivity.isConnected(getApplicationContext())) {
            homePresenter.getRoutes();
        } else {
            hideProgress();
            ToastUtils.showToast(ctx, getString(R.string.please_check_your_internet_connection));
        }
    }
    /*it will return the route list that available*/
    @Override
    public void getRoutesSuccess(BusRoute routesList) {
        busRoutesList.clear();
        busRoutesList.addAll(routesList.getRoutes());
        routeAdapter.notifyDataSetChanged();

    }
    /*It will be triggered during network issue or error*/
    @Override
    public void getRoutesFailed(String failed) {
        ToastUtils.showToast(this, failed);
    }

    /*To dismiss the progress bar*/
    @Override
    public void showProgress() {
        if (!swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.setRefreshing(true);
    }
    /*To show the progress bar*/
    @Override
    public void hideProgress() {
        if (swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.setRefreshing(false);
    }

    /* It will return clicked position of recyclerview*/
    @Override
    public void onRouteRowClicked(int position) {
        Intent intent = new Intent(getBaseContext(), DetailActivity.class);
        Route route = new Route(busRoutesList.get(position).getId(),
                busRoutesList.get(position).getName(),
                busRoutesList.get(position).getStops(),
                busRoutesList.get(position).getDescription(),
                busRoutesList.get(position).getAccessible(),
                busRoutesList.get(position).getImage());
        intent.putExtra("route",route);
        startActivity(intent);
        overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
    }
    /*For pulltorefresh*/
    @Override
    public void onRefresh() {
        getRoutes();
    }
}
