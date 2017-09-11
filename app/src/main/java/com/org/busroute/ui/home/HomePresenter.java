package com.org.busroute.ui.home;
import android.content.Context;
import com.org.busroute.model.BusRoute;
import com.org.busroute.network.ApiClient;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
/**
 * This  is HomePresenter
 */
public class HomePresenter implements IHomePresenter {
    IHomeView homeView;
    Context ctx;
    //RxJava
    private Subscription subscription;

    public HomePresenter(IHomeView homeView) {
        this.homeView = homeView;
    }
    /*to get the route list from server*/
    @Override
    public void getRoutes() {

        homeView.showProgress();
        subscription = ApiClient.getInstance().getRoutes()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<BusRoute>() {
                    @Override
                    public void onCompleted() {
                        homeView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        homeView.getRoutesFailed("onError");
                        homeView.hideProgress();
                    }

                    @Override
                    public void onNext(BusRoute routesList) {
                        homeView.hideProgress();
                        homeView.getRoutesSuccess(routesList);
                       /* System.out.println(TAG + "--->" + new Gson().toJson(routesList));*/
                    }
                });
    }
    /*For unsubscribing Rx java*/
    @Override
    public void destory() {
        this.homeView = null;
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
