package com.bw.movie.di.model;

import android.util.Log;

import com.bw.movie.data.bean.UpdatePwdBean;
import com.bw.movie.data.net.ApiService;
import com.bw.movie.data.net.NetUtils;
import com.bw.movie.di.contract.CzpwdContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 重置密码
 * 张娜
 */
public class CzpwdModel implements CzpwdContract.Model {

    @Override
    public void reponseData(int userid, String sessionid, String encrypt1, String encrypt2, String encrypt3, final CallBack callBack) {
        //修改
        ApiService service = NetUtils.getinstance(ApiService.class);
        Call<UpdatePwdBean> call = service.updatepass(userid, sessionid, encrypt1, encrypt2, encrypt3);
        call.enqueue(new Callback<UpdatePwdBean>() {
            @Override
            public void onResponse(Call<UpdatePwdBean> call, Response<UpdatePwdBean> response) {
                UpdatePwdBean bean = response.body();
                Log.d("CzpwdModel", bean.getMessage());
                callBack.oncall(bean);
            }

            @Override
            public void onFailure(Call<UpdatePwdBean> call, Throwable t) {
                Log.d("CzpwdModel", "t:" + t);
            }
        });

    }
}
