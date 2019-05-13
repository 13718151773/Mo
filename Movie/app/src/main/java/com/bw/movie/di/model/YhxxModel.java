package com.bw.movie.di.model;

import com.bw.movie.data.bean.MyMessageBean;
import com.bw.movie.data.bean.SctxBean;
import com.bw.movie.data.net.ApiService;
import com.bw.movie.data.net.NetUtils;
import com.bw.movie.di.contract.YhxxContract;

import okhttp3.MultipartBody;
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

    //上传头像
    @Override
    public void reponseData2(int userid, String sessionid, MultipartBody.Part part, final CallBack callBack) {
        ApiService service = NetUtils.getinstance(ApiService.class);
        Call<SctxBean> call = service.sctx(userid, sessionid, part);
        call.enqueue(new Callback<SctxBean>() {
            @Override
            public void onResponse(Call<SctxBean> call, Response<SctxBean> response) {
                SctxBean bean = response.body();
                callBack.oncall2(bean);
            }

            @Override
            public void onFailure(Call<SctxBean> call, Throwable t) {

            }
        });
    }
}
