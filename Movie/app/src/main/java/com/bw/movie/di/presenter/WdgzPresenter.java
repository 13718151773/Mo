package com.bw.movie.di.presenter;

import com.bw.movie.data.bean.MyGzYpBean;
import com.bw.movie.di.contract.WdgzContract;
import com.bw.movie.di.model.WdgzModel;

import java.lang.ref.SoftReference;

/**
 * 我的关注
 * 张娜
 */
public class WdgzPresenter implements WdgzContract.Presenter {

    WdgzContract.View view;
    private SoftReference<WdgzContract.View> reference;
    private WdgzModel model;

    @Override
    public void attachView(WdgzContract.View view) {
        this.view=view;
        reference = new SoftReference<>(view);
        model = new WdgzModel();
    }

    @Override
    public void dattachView(WdgzContract.View view) {
        reference.clear();
    }

    @Override
    public void requestData(int userid, String sessionid, int i, int i1) {
        model.reponseData(userid,sessionid,i,i1,new WdgzContract.Model.CallBack() {
            @Override
            public void oncall(MyGzYpBean bean) {
                view.showData(bean);
            }
        });
    }
}
