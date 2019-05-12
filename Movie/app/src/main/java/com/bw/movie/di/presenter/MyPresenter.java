package com.bw.movie.di.presenter;

import com.bw.movie.data.bean.MyMessageBean;
import com.bw.movie.data.bean.QdBean;
import com.bw.movie.di.contract.MyContract;
import com.bw.movie.di.model.MyModel;

import java.lang.ref.SoftReference;

/**
 * 张娜
 * 我的p
 */
public class MyPresenter implements MyContract.Presenter {

    MyContract.View view;
    private SoftReference<MyContract.View> reference;
    private MyModel model;

    @Override
    public void attachView(MyContract.View view) {
        this.view=view;
        reference = new SoftReference<>(view);
        model = new MyModel();
    }

    @Override
    public void dattachView(MyContract.View view) {
        reference.clear();
    }

    @Override
    public void requestData(int userid, String sessionid) {
        model.reponseData(userid,sessionid,new MyContract.Model.CallBack() {
            //我的信息
            @Override
            public void oncall(MyMessageBean bean) {
                view.showData(bean);
            }

            //签到
            @Override
            public void oncall2(QdBean bean) {
                view.showData2(bean);
            }
        });
    }
}
