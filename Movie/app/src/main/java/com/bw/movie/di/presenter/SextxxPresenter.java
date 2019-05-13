package com.bw.movie.di.presenter;

import com.bw.movie.data.bean.SextxxBean;
import com.bw.movie.data.bean.UpdateReadMesBean;
import com.bw.movie.data.bean.WdBean;
import com.bw.movie.di.contract.SextxxContract;
import com.bw.movie.di.model.SextxxModel;

import java.lang.ref.SoftReference;

/**
 * 系统消息
 * 张娜
 */
public class SextxxPresenter implements SextxxContract.Presenter {

    SextxxContract.View view;
    private SoftReference<SextxxContract.View> reference;
    private SextxxModel model;

    @Override
    public void attachView(SextxxContract.View view) {
        this.view=view;
        reference = new SoftReference<>(view);
        model = new SextxxModel();
    }

    @Override
    public void dattachView(SextxxContract.View view) {
        reference.clear();
    }

    //查询信息
    @Override
    public void requestData(int userid, String sessionid, int i, int i1) {
        model.reponseData(userid,sessionid,i,i1,new SextxxContract.Model.CallBack() {
            @Override
            public void oncall(SextxxBean bean) {
                view.showData(bean);
            }

            @Override
            public void oncall2(WdBean bean) {

            }

            @Override
            public void oncall3(UpdateReadMesBean bean) {

            }
        });
    }

    //修改状态
    @Override
    public void requestData2(int userid, String sessionid, int id) {
        model.reponseData2(userid,sessionid,id,new SextxxContract.Model.CallBack() {
            @Override
            public void oncall(SextxxBean bean) {

            }

            @Override
            public void oncall2(WdBean bean) {

            }

            @Override
            public void oncall3(UpdateReadMesBean bean) {
                view.showData3(bean);
            }
        });
    }

    //查询数量
    @Override
    public void requestData3(int userid, String sessionid) {
        model.reponseData3(userid,sessionid,new SextxxContract.Model.CallBack() {
            @Override
            public void oncall(SextxxBean bean) {

            }

            @Override
            public void oncall2(WdBean bean) {
                view.showData2(bean);
            }

            @Override
            public void oncall3(UpdateReadMesBean bean) {
                view.showData3(bean);
            }
        });
    }
}
