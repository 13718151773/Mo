package com.bw.movie.di.model;

import com.bw.movie.data.bean.MyMessageBean;
import com.bw.movie.data.bean.QdBean;
import com.bw.movie.data.net.ApiService;
import com.bw.movie.data.net.NetUtils;
import com.bw.movie.di.contract.MyContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 张娜
 * 我的 m
 */
public class MyModel implements MyContract.Model {
    @Override
    public void reponseData(int userid, String sessionid, final CallBack callBack) {

        //我的信息
        ApiService service = NetUtils.getinstance(ApiService.class);
        Call<MyMessageBean> call = service.yhxx(userid, sessionid);
        call.enqueue(new Callback<MyMessageBean>() {
            @Override
            public void onResponse(Call<MyMessageBean> call, Response<MyMessageBean> response) {
                MyMessageBean bean = response.body();
                callBack.oncall(bean);
            }

            @Override
            public void onFailure(Call<MyMessageBean> call, Throwable t) {

            }
        });

        //签到
        ApiService service1 = NetUtils.getinstance(ApiService.class);
        Call<QdBean> call1 = service1.qd(userid, sessionid);
        call1.enqueue(new Callback<QdBean>() {
            @Override
            public void onResponse(Call<QdBean> call, Response<QdBean> response) {
                QdBean bean = response.body();
                callBack.oncall2(bean);
            }

            @Override
            public void onFailure(Call<QdBean> call, Throwable t) {

            }
        });


    }
}
