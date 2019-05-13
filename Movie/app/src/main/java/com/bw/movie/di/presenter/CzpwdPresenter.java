package com.bw.movie.di.presenter;

import com.bw.movie.data.bean.UpdatePwdBean;
import com.bw.movie.di.contract.CzpwdContract;
import com.bw.movie.di.model.CzpwdModel;

import java.lang.ref.SoftReference;

/**
 * 重置密码
 * 张娜
 */
public class CzpwdPresenter implements CzpwdContract.Presenter {

    CzpwdContract.View view;
    private SoftReference<CzpwdContract.View> reference;
    private CzpwdModel model;

    @Override
    public void attachView(CzpwdContract.View view) {
        this.view = view;
        reference = new SoftReference<>(view);
        model = new CzpwdModel();
    }

    @Override
    public void dattachView(CzpwdContract.View view) {
        reference.clear();
    }

    @Override
    public void requestData(int userid, String sessionid, String encrypt1, String encrypt2, String encrypt3) {
        model.reponseData(userid,sessionid,encrypt1,encrypt2,encrypt3,new CzpwdContract.Model.CallBack() {
            @Override
            public void oncall(UpdatePwdBean bean) {
                view.showData(bean);
            }
        });
    }
}
