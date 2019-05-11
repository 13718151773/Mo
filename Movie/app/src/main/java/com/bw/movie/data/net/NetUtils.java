package com.bw.movie.data.net;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 张娜 网络请求
 */
public class NetUtils {

    private static NetUtils netUtils;

    private NetUtils() {

    }

    public static  <T> T getinstance(Class<T> service) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.all)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        T t = retrofit.create(service);

        return t;

    }


}
