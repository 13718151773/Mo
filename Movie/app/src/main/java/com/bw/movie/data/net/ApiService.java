package com.bw.movie.data.net;

import com.bw.movie.data.bean.LoginBean;
import com.bw.movie.data.bean.MyMessageBean;
import com.bw.movie.data.bean.NewVerSessionBean;
import com.bw.movie.data.bean.QdBean;
import com.bw.movie.data.bean.RegisterBean;
import com.bw.movie.data.bean.SctxBean;
import com.bw.movie.data.bean.SextxxBean;
import com.bw.movie.data.bean.UpdatePwdBean;
import com.bw.movie.data.bean.UpdateReadMesBean;
import com.bw.movie.data.bean.WdBean;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * 张娜
 * 请求的类
 */
public interface ApiService {

    //注册 zn
    @FormUrlEncoded
    @POST("movieApi/user/v1/registerUser")
    Call<RegisterBean> zc(@Field("nickName") String nickName, @Field("phone") String phone,
                          @Field("pwd") String pwd, @Field("pwd2") String pwd2,
                          @Field("sex") int sex, @Field("birthday") String birthday,
                          @Field("imei") String imei, @Field("ua") String ua,
                          @Field("screenSize") String screenSize, @Field("os") String os,
                          @Field("email") String email);

    //登录 zn
    @FormUrlEncoded
    @POST("movieApi/user/v1/login")
    Call<LoginBean> dl(@Field("phone") String phone, @Field("pwd") String pwd);

    //根据用户ID查询用户信息 zn
    @GET("movieApi/user/v1/verify/getUserInfoByUserId")
    Call<MyMessageBean> yhxx(@Header("userid") int userid, @Header("sessionid") String sessionid);

    //用户签到
    @GET("movieApi/user/v1/verify/userSignIn")
    Call<QdBean> qd(@Header("userid") int userid, @Header("sessionid") String sessionid);

    //上传头像
    @Multipart
    @POST("movieApi/user/v1/verify/uploadHeadPic")
    Call<SctxBean> sctx(@Header("userid") int userid, @Header("sessionid") String sessionid,
                        @Part MultipartBody.Part file);

    //修改密码
    @FormUrlEncoded
    @POST("movieApi/user/v1/verify/modifyUserPwd")
    Call<UpdatePwdBean> updatepass(@Header("userid") int userid, @Header("sessionid") String sessionid,
                                   @Field("oldPwd") String oldPwd, @Field("newPwd") String newPwd,
                                   @Field("newPwd2") String newPwd2);

    //查询系统消息列表
    @GET("movieApi/tool/v1/verify/findAllSysMsgList")
    Call<SextxxBean> sextxx(@Header("userid") int userid, @Header("sessionid") String sessionid,
                            @Query("page") int page, @Query("count") int count);

    //查询用户当前未读消息数量
    @GET("movieApi/tool/v1/verify/findUnreadMessageCount")
    Call<WdBean> wd(@Header("userid") int userid, @Header("sessionid") String sessionid);

    //系统读取状态修改
    @GET("movieApi/tool/v1/verify/changeSysMsgStatus")
    Call<UpdateReadMesBean> upstatus(@Header("userid") int userid, @Header("sessionid") String sessionid,
                                     @Query("id") int id);

    //查询新版本
    @GET("movieApi/tool/v1/findNewVersion")
    Call<NewVerSessionBean> news(@Header("userid") int userid, @Header("sessionid") String sessionid
            , @Header("ak") String ak);


}
