package com.org.busroute.ui.home;
import com.org.busroute.model.BusRoute;
/**
 * This  is IHomeView
 */
public interface IHomeView {
    void getRoutesSuccess(BusRoute routesList);

    void getRoutesFailed(String failed);

    void showProgress();

    void hideProgress();
}
