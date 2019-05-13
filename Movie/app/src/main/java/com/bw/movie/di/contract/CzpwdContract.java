package com.bw.movie.di.contract;

import com.bw.movie.data.bean.UpdatePwdBean;

/**
 * 重置密码
 */
public interface CzpwdContract {

    interface View {
        void showData(UpdatePwdBean bean);
    }

    interface Presenter<T extends View> {
        void attachView(T t);

        void dattachView(T t);

        void requestData(int userid, String sessionid, String encrypt1, String encrypt2, String encrypt3);
    }

    interface Model {
        void reponseData(int userid, String sessionid, String encrypt1, String encrypt2, String encrypt3, CallBack callBack);

        interface CallBack {
            void oncall(UpdatePwdBean bean);
        }
    }
}
