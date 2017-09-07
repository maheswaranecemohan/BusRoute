package com.org.busroute.ui.home;
import android.content.Context;
import com.google.gson.Gson;
import com.org.busroute.model.BusRoute;
import com.org.busroute.network.ApiClient;
import com.org.busroute.utils.LogUtils;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;
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

    @Override
    public void getRoutes() {

        homeView.showProgress();
        subscription = ApiClient.getInstance()
                .getRoutes()
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
                        LogUtils.LOGI(TAG, "--->" + e.toString());
                        homeView.getRoutesFailed("onError");
                        homeView.hideProgress();
                    }

                    @Override
                    public void onNext(BusRoute routesList) {
                        homeView.hideProgress();
                        homeView.getRoutesSuccess(routesList);
                        System.out.println(TAG + "--->" + new Gson().toJson(routesList));
                    }
                });
    }

    @Override
    public void destory() {
        this.homeView = null;
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
