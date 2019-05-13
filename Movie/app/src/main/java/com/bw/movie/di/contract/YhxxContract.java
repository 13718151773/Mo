package com.bw.movie.di.contract;

import com.bw.movie.data.bean.MyMessageBean;
import com.bw.movie.data.bean.SctxBean;

import okhttp3.MultipartBody;

/**
 * 用户信息
 */
public interface YhxxContract {

    interface View {
        void showData(MyMessageBean bean);

        void showData2(SctxBean bean);
    }

    interface Presenter <T extends View>{
        void attachView(T t);

        void dattachView(T t);

        void requestData(int userid, String sessionid);

        void requestData2(int userid, String sessionid, MultipartBody.Part part);
    }

    interface Model {
        void reponseData(int userid, String sessionid, CallBack callBack);

        void reponseData2(int userid, String sessionid, MultipartBody.Part part, CallBack callBack);

        interface CallBack{
            void oncall(MyMessageBean bean);

            void oncall2(SctxBean bean);
        }
    }
}
