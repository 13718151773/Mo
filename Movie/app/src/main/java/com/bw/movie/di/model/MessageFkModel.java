package com.bw.movie.di.model;

import android.support.v4.widget.SwipeRefreshLayout;

import com.bw.movie.data.bean.MessageFkBean;
import com.bw.movie.data.net.Api;
import com.bw.movie.data.net.ApiService;
import com.bw.movie.data.net.NetUtils;
import com.bw.movie.di.contract.MessageFkContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 意见反馈
 * 张娜
 */
public class MessageFkModel implements MessageFkContract.Model {
    @Override
    public void reponseData(int userid, String sessionid, String s, final CallBack callBack) {

        //意见反馈
        ApiService service = NetUtils.getinstance(ApiService.class);
        Call<MessageFkBean> call = service.fk(userid, sessionid, s);
        call.enqueue(new Callback<MessageFkBean>() {
            @Override
            public void onResponse(Call<MessageFkBean> call, Response<MessageFkBean> response) {
                MessageFkBean bean = response.body();
                callBack.oncall(bean);
            }

            @Override
            public void onFailure(Call<MessageFkBean> call, Throwable t) {

            }
        });


    }
}
