package com.bw.movie.di.contract;

import com.bw.movie.data.bean.GzBean;
import com.bw.movie.data.bean.QxGzBean;
import com.bw.movie.data.bean.TuijYyBean;

/**
 * 影院
 *
 */
public interface FragCinemaContract {

    interface View {
        void showData(TuijYyBean bean);

        void showData2(GzBean bean);

        void showData3(QxGzBean bean);
    }

    interface Presenter <T extends View>{
        void attachView(T t);

        void dattachView(T t);

        void requestData(int userid, String sessionid, int i, int i1);

        void requestData2(int userid, String sessionid, String s, String s1, int i, int i1);

        void requestData3(int userid, String sessionid, int id);

        void requestData4(int userid, String sessionid, int id);
    }

    interface Model {
        void reponseData(int userid, String sessionid, int i, int i1, CallBack callBack);

        void reponseData2(int userid, String sessionid, int id, CallBack callBack);

        interface CallBack{
            void oncall(TuijYyBean bean);

            void oncall2(GzBean bean);

            void oncall3(QxGzBean bean);
        }
    }
}
