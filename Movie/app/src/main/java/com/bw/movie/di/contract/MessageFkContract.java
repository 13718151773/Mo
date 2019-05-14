package com.bw.movie.di.contract;

import com.bw.movie.data.bean.MessageFkBean;

/**
 * 意见反馈
 * 张娜
 */
public interface MessageFkContract {

    interface View {
        void showData(MessageFkBean bean);
    }

    interface Presenter<T extends View> {
        void attachView(T t);

        void dattachView(T t);

        void requestData(int userid, String sessionid, String s);
    }

    interface Model {
        void reponseData(int userid, String sessionid, String s, CallBack callBack);

        interface CallBack {
            void oncall(MessageFkBean bean);
        }
    }
}
