package com.bw.movie.di.contract;

import com.bw.movie.data.bean.MyGzYpBean;

/**
 * 我的关注
 * 张娜
 */
public interface WdgzContract {

    interface View {
        void showData(MyGzYpBean bean);
    }

    interface Presenter<T extends View> {
        void attachView(T t);

        void dattachView(T t);

        void requestData(int userid, String sessionid, int i, int i1);
    }

    interface Model {
        void reponseData(int userid, String sessionid, int i, int i1, CallBack callBack);

        interface CallBack {
            void oncall(MyGzYpBean bean);
        }
    }
}
