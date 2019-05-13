package com.bw.movie.di.model;

import com.bw.movie.data.bean.SextxxBean;
import com.bw.movie.data.bean.UpdateReadMesBean;
import com.bw.movie.data.bean.WdBean;
import com.bw.movie.data.net.ApiService;
import com.bw.movie.data.net.NetUtils;
import com.bw.movie.di.contract.SextxxContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 系统消息
 * 张娜
 */
public class SextxxModel implements SextxxContract.Model {
    //查询信息
    @Override
    public void reponseData(int userid, String sessionid, int i, int i1, final CallBack callBack) {
        ApiService service = NetUtils.getinstance(ApiService.class);
        Call<SextxxBean> call = service.sextxx(userid, sessionid, i, i1);
        call.enqueue(new Callback<SextxxBean>() {
            @Override
            public void onResponse(Call<SextxxBean> call, Response<SextxxBean> response) {
                SextxxBean bean = response.body();
                callBack.oncall(bean);
            }

            @Override
            public void onFailure(Call<SextxxBean> call, Throwable t) {

            }
        });
    }

    //修改状态
    @Override
    public void reponseData2(int userid, String sessionid, int id, final CallBack callBack) {
        ApiService service = NetUtils.getinstance(ApiService.class);
        Call<UpdateReadMesBean> call = service.upstatus(userid, sessionid, id);
        call.enqueue(new Callback<UpdateReadMesBean>() {
            @Override
            public void onResponse(Call<UpdateReadMesBean> call, Response<UpdateReadMesBean> response) {
                UpdateReadMesBean bean = response.body();
                callBack.oncall3(bean);
            }

            @Override
            public void onFailure(Call<UpdateReadMesBean> call, Throwable t) {

            }
        });

    }

    //未读消息数量
    @Override
    public void reponseData3(int userid, String sessionid, final CallBack callBack) {
        ApiService service1 = NetUtils.getinstance(ApiService.class);
        final Call<WdBean> call1 = service1.wd(userid, sessionid);
        call1.enqueue(new Callback<WdBean>() {
            @Override
            public void onResponse(Call<WdBean> call, Response<WdBean> response) {
                WdBean bean = response.body();
                callBack.oncall2(bean);
            }

            @Override
            public void onFailure(Call<WdBean> call, Throwable t) {

            }
        });

    }
}
