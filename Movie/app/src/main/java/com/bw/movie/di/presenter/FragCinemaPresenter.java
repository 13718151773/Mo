package com.bw.movie.di.presenter;

import com.bw.movie.data.bean.GzBean;
import com.bw.movie.data.bean.QxGzBean;
import com.bw.movie.data.bean.TuijYyBean;
import com.bw.movie.di.contract.FragCinemaContract;
import com.bw.movie.di.model.FragCinemaModel;

import java.lang.ref.SoftReference;

/**
 * 影院
 */
public class FragCinemaPresenter implements FragCinemaContract.Presenter {

    FragCinemaContract.View view;
    private SoftReference<FragCinemaContract.View> reference;
    private FragCinemaModel model;

    @Override
    public void attachView(FragCinemaContract.View view) {
        this.view=view;
        reference = new SoftReference<>(view);
        model = new FragCinemaModel();
    }

    @Override
    public void dattachView(FragCinemaContract.View view) {
        reference.clear();
    }

    //推荐影院
    @Override
    public void requestData(int userid, String sessionid, int i, int i1) {
        model.reponseData(userid,sessionid,i,i1,new FragCinemaContract.Model.CallBack() {
            @Override
            public void oncall(TuijYyBean bean) {
                view.showData(bean);
            }

            @Override
            public void oncall2(GzBean bean) {

            }

            @Override
            public void oncall3(QxGzBean bean) {

            }
        });
    }

    //附近
    @Override
    public void requestData2(int userid, String sessionid, String s, String s1, int i, int i1) {

    }

    //关注
    @Override
    public void requestData3(int userid, String sessionid, int id) {
        model.reponseData2(userid,sessionid,id,new FragCinemaContract.Model.CallBack() {
            @Override
            public void oncall(TuijYyBean bean) {

            }

            @Override
            public void oncall2(GzBean bean) {
                view.showData2(bean);
            }

            @Override
            public void oncall3(QxGzBean bean) {

            }
        });
    }

    //取消关注
    @Override
    public void requestData4(int userid, String sessionid, int id) {
        model.reponseData2(userid,sessionid,id,new FragCinemaContract.Model.CallBack() {
            @Override
            public void oncall(TuijYyBean bean) {

            }

            @Override
            public void oncall2(GzBean bean) {

            }

            @Override
            public void oncall3(QxGzBean bean) {
                view.showData3(bean);
            }

        });
    }
}
