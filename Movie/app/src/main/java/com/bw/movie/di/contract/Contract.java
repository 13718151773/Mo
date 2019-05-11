package com.bw.movie.di.contract;

import com.bw.movie.data.bean.LoginBean;

/**
 * 张娜
 * 登录
 * mvp c
 */
public interface Contract {

    interface View {
        void showData(LoginBean bean);
    }

    interface Presenter <T extends View>{
        void attachView(T t);

        void dattachView(T t);

        void requestData(String phones, String texts);
    }

    interface Model {
        void reponseData(String phones, String texts, CallBack callBack);
        public interface CallBack{
            public void oncall(LoginBean bean);
        }
    }
}
