package com.bw.movie.di.presenter;

import com.bw.movie.data.bean.MessageFkBean;
import com.bw.movie.di.contract.MessageFkContract;
import com.bw.movie.di.model.MessageFkModel;

import java.lang.ref.SoftReference;

/**
 * 意见反馈
 * 张娜
 */
public class MessageFkPresenter implements MessageFkContract.Presenter {

    MessageFkContract.View view;
    private SoftReference<MessageFkContract.View> reference;
    private MessageFkModel model;

    @Override
    public void attachView(MessageFkContract.View view) {
        this.view=view;
        reference = new SoftReference<>(view);
        model = new MessageFkModel();
    }

    @Override
    public void dattachView(MessageFkContract.View view) {
        reference.clear();
    }

    @Override
    public void requestData(int userid, String sessionid, String s) {
        model.reponseData(userid,sessionid,s,new MessageFkContract.Model.CallBack() {
            @Override
            public void oncall(MessageFkBean bean) {
                view.showData(bean);
            }
        });
    }
}
