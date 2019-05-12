package com.bw.movie.di.contract;

import com.bw.movie.data.bean.MyMessageBean;

/**
 * 用户信息
 */
public interface YhxxContract {

    interface View {
        void showData(MyMessageBean bean);
    }

    interface Presenter <T extends View>{
        void attachView(T t);

        void dattachView(T t);

        void requestData(int userid, String sessionid);
    }

    interface Model {
        void reponseData(int userid, String sessionid, CallBack callBack);
        interface CallBack{
            void oncall(MyMessageBean bean);
        }
    }
}
