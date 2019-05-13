package com.bw.movie.di.contract;

import com.bw.movie.data.bean.MyMessageBean;
import com.bw.movie.data.bean.NewVerSessionBean;
import com.bw.movie.data.bean.QdBean;
import com.bw.movie.data.bean.SctxBean;

/**
 * 张娜
 * 我的fragment
 */
public interface MyContract {

    interface View {
        void showData(MyMessageBean bean);

        void showData2(QdBean bean);


        void showData3(NewVerSessionBean bean);
    }

    interface Presenter<T extends View> {
        void attachView(T t);

        void dattachView(T t);

        void requestData(int userid, String sessionid);


        void requestData3(int userid, String sessionid);

        void requestData4(int userid, String sessionid);
    }

    interface Model {
        void reponseData(int userid, String sessionid, CallBack callBack);


        public interface CallBack {
            void oncall(MyMessageBean bean);

            void oncall2(QdBean bean);

            void oncall3(SctxBean bean);

            void oncall4(NewVerSessionBean bean);
        }
    }
}
