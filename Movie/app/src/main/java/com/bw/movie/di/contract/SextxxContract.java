package com.bw.movie.di.contract;

import com.bw.movie.data.bean.SextxxBean;
import com.bw.movie.data.bean.UpdateReadMesBean;
import com.bw.movie.data.bean.WdBean;

/**
 * 系统消息
 * 张娜
 */
public interface SextxxContract {

    interface View {
        void showData(SextxxBean bean);

        void showData2(WdBean bean);

        void showData3(UpdateReadMesBean bean);
    }

    interface Presenter<T extends View> {
        void attachView(T t);

        void dattachView(T t);

        void requestData(int userid, String sessionid, int i, int i1);

        void requestData2(int userid, String sessionid, int id);

        void requestData3(int userid, String sessionid);
    }

    interface Model {
        void reponseData(int userid, String sessionid, int i, int i1, CallBack callBack);

        void reponseData2(int userid, String sessionid, int id, CallBack callBack);

        void reponseData3(int userid, String sessionid, CallBack callBack);

        interface CallBack {
            void oncall(SextxxBean bean);

            void oncall2(WdBean bean);

            void oncall3(UpdateReadMesBean bean);
        }
    }
}
