package com.bw.movie.di.model;

import com.bw.movie.data.bean.GzBean;
import com.bw.movie.data.bean.QxGzBean;
import com.bw.movie.data.bean.TuijYyBean;
import com.bw.movie.data.net.ApiService;
import com.bw.movie.data.net.NetUtils;
import com.bw.movie.di.contract.FragCinemaContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 影院
 */
public class FragCinemaModel implements FragCinemaContract.Model {
    @Override
    public void reponseData(int userid, String sessionid, int i, int i1, final CallBack callBack) {
        ApiService service = NetUtils.getinstance(ApiService.class);
        Call<TuijYyBean> call = service.tjyy(userid, sessionid, i, i1);
        call.enqueue(new Callback<TuijYyBean>() {
            @Override
            public void onResponse(Call<TuijYyBean> call, Response<TuijYyBean> response) {
                TuijYyBean bean = response.body();
                callBack.oncall(bean);
            }

            @Override
            public void onFailure(Call<TuijYyBean> call, Throwable t) {

            }
        });
    }

    //关注
    @Override
    public void reponseData2(int userid, String sessionid, int id, final CallBack callBack) {
        ApiService service = NetUtils.getinstance(ApiService.class);
        Call<GzBean> call = service.gz(userid, sessionid, id);
        call.enqueue(new Callback<GzBean>() {
            @Override
            public void onResponse(Call<GzBean> call, Response<GzBean> response) {
                GzBean bean = response.body();
                callBack.oncall2(bean);
            }

            @Override
            public void onFailure(Call<GzBean> call, Throwable t) {

            }
        });

        //不在关注
        ApiService service1 = NetUtils.getinstance(ApiService.class);
        Call<QxGzBean> call1 = service1.qxgzyy(userid, sessionid, id);
        call1.enqueue(new Callback<QxGzBean>() {
            @Override
            public void onResponse(Call<QxGzBean> call, Response<QxGzBean> response) {
                QxGzBean bean = response.body();
                callBack.oncall3(bean);
            }

            @Override
            public void onFailure(Call<QxGzBean> call, Throwable t) {

            }
        });


    }


}
