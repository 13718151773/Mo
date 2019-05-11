package com.bw.movie.di.model;

import android.util.Log;

import com.bw.movie.data.bean.RegisterBean;
import com.bw.movie.data.net.ApiService;
import com.bw.movie.data.net.NetUtils;
import com.bw.movie.di.contract.RegisterContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 张娜
 * 注册
 *
 */
public class RegisterModel implements RegisterContract.Model {
    @Override
    public void reponseData(String names, String phones, String encrypt, String s, int xb, String dates,
                            String emails, final CallBack callBack) {

        // 昵称 手机号 密码 重复密码 性别（1 男） 出生日期
        //移动设备唯一识别码 设备类型 屏幕尺寸 手机系统
        // 邮箱

        ApiService service = NetUtils.getinstance(ApiService.class);
        Call<RegisterBean> call = service.zc(names, phones, encrypt, s, xb, dates, "123456", "小米", "5.0", "android", emails);
        call.enqueue(new Callback<RegisterBean>() {
            @Override
            public void onResponse(Call<RegisterBean> call, Response<RegisterBean> response) {
                RegisterBean bean = response.body();
                callBack.oncall(bean);
            }

            @Override
            public void onFailure(Call<RegisterBean> call, Throwable t) {
                Log.d("RegisterModel", "t:" + t);
            }
        });


    }
}
