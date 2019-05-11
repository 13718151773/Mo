package com.bw.movie.di.contract;

import com.bw.movie.data.bean.RegisterBean;

/**
 * 张娜
 * 注册
 *
 */
public interface RegisterContract {

    interface View {
        void showData(RegisterBean bean);
    }

    interface Presenter <T extends View>{
        void attachView(T t);

        void dattachView(T t);

        void requestData(String names, String phones, String encrypt, String s, int xb, String dates, String emails);
    }
    interface Model {
        void reponseData(String names, String phones, String encrypt, String s, int xb, String dates, String emails, CallBack callBack);
        interface CallBack{
            void oncall(RegisterBean bean);
        }
    }
}
