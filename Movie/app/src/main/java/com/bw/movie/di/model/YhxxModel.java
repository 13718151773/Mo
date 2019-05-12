package com.bw.movie.di.model;

import com.bw.movie.data.bean.MyMessageBean;
import com.bw.movie.data.net.ApiService;
import com.bw.movie.data.net.NetUtils;
import com.bw.movie.di.contract.YhxxContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 用户信息 m
 */
public class YhxxModel implements YhxxContract.Model {
    @Override
    public void reponseData(int userid, String sessionid, final CallBack callBack) {

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

    }
}
