package com.bw.movie.di.model;

import android.util.Log;

import com.bw.movie.data.bean.MyGzYpBean;
import com.bw.movie.data.net.ApiService;
import com.bw.movie.data.net.NetUtils;
import com.bw.movie.di.contract.WdgzContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 我的关注
 * 张娜
 */
public class WdgzModel implements WdgzContract.Model {
    @Override
    public void reponseData(int userid, String sessionid, int i, int i1, final CallBack callBack) {
        ApiService service = NetUtils.getinstance(ApiService.class);
        Call<MyGzYpBean> call = service.gzyp(userid, sessionid, i, i1);
        call.enqueue(new Callback<MyGzYpBean>() {
            @Override
            public void onResponse(Call<MyGzYpBean> call, Response<MyGzYpBean> response) {
                MyGzYpBean bean = response.body();
                callBack.oncall(bean);
            }

            @Override
            public void onFailure(Call<MyGzYpBean> call, Throwable t) {
            }
        });


    }
}
