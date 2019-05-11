package com.bw.movie.data.net;

import com.bw.movie.data.bean.LoginBean;
import com.bw.movie.data.bean.RegisterBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 张娜
 * 请求的类
 *
 */
public interface ApiService {

    //注册 zn
    @FormUrlEncoded
    @POST("movieApi/user/v1/registerUser")
    Call<RegisterBean> zc(@Field("nickName") String nickName, @Field("phone")String phone,
                          @Field("pwd")String pwd, @Field("pwd2")String pwd2,
                          @Field("sex")int sex, @Field("birthday")String birthday,
                          @Field("imei")String imei, @Field("ua")String ua,
                          @Field("screenSize")String screenSize, @Field("os")String os,
                          @Field("email")String email);

    //登录 zn
    @FormUrlEncoded
    @POST("movieApi/user/v1/login")
    Call<LoginBean> dl(@Field("phone")String phone,@Field("pwd")String pwd);



}
