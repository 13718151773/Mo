package com.bw.movie.di.model;

import android.util.Log;

import com.bw.movie.data.bean.LoginBean;
import com.bw.movie.data.net.ApiService;
import com.bw.movie.data.net.NetUtils;
import com.bw.movie.di.contract.Contract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 张娜
 * 登录
 * MVP m
 */
public class Model implements Contract.Model {
    @Override
    public void reponseData(String phones, String texts, final CallBack callBack) {

        ApiService service = NetUtils.getinstance(ApiService.class);
        Call<LoginBean> call = service.dl(phones, texts);
        call.enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                LoginBean bean = response.body();
                callBack.oncall(bean);
            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {
                Log.d("Model", "t:" + t);
            }
        });

    }
}
