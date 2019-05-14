package com.bw.movie.di.model;
//类注释设置模板


import android.util.Log;

import com.bw.movie.data.bean.CommingSoonBeen;
import com.bw.movie.data.bean.HotBeen;
import com.bw.movie.data.bean.ReplaceBeen;
import com.bw.movie.data.net.ApiService;
import com.bw.movie.data.net.NetUtils;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Description: $description$ 类（或接口）是
 * @Author: yuhua
 * @Date: $date$
 */
public class MovieMolde {
    MyCallBack myCallBack,myCallBack1,myCallBack2;



    public void hot(int userld, String seccessionld, Map<String, String> map) {
        ApiService getinstance = NetUtils.getinstance(ApiService.class);
        Call<HotBeen> hot = getinstance.hot(userld, seccessionld, map);
        hot.enqueue(new Callback<HotBeen>() {
            @Override
            public void onResponse(Call<HotBeen> call, Response<HotBeen> response) {
                HotBeen body = response.body();
                myCallBack.success(body);
            }

            @Override
            public void onFailure(Call<HotBeen> call, Throwable t) {

            }
        });

    }

    public void replace(int userld, String seccessionld, Map<String, String> map) {
        ApiService getinstance = NetUtils.getinstance(ApiService.class);
        Call<ReplaceBeen> release = getinstance.release(userld, seccessionld, map);
        release.enqueue(new Callback<ReplaceBeen>() {
            @Override
            public void onResponse(Call<ReplaceBeen> call, Response<ReplaceBeen> response) {
                ReplaceBeen body = response.body();
                myCallBack1.success(body);
            }

            @Override
            public void onFailure(Call<ReplaceBeen> call, Throwable t) {

            }
        });

    }

    public void comming(int userld, String seccessionld, Map<String, String> map) {
        ApiService getinstance = NetUtils.getinstance(ApiService.class);
        Call<CommingSoonBeen> omingoon = getinstance.omingoon(userld, seccessionld, map);
        omingoon.enqueue(new Callback<CommingSoonBeen>() {
            @Override
            public void onResponse(Call<CommingSoonBeen> call, Response<CommingSoonBeen> response) {
                CommingSoonBeen body = response.body();
                myCallBack2.success(body);
            }

            @Override
            public void onFailure(Call<CommingSoonBeen> call, Throwable t) {

            }
        });
    }


    public void setMyCallBack(MyCallBack myCallBack) {
        this.myCallBack = myCallBack;
    }

    public void setMyCallBack1(MyCallBack myCallBack1) {
        this.myCallBack1 = myCallBack1;
    }

    public void setMyCallBack2(MyCallBack myCallBack2) {
        this.myCallBack2 = myCallBack2;
    }

    public interface MyCallBack {
        void success(Object o);
    }
}
