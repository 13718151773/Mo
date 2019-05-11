package com.bw.movie.di.presenter;

import com.bw.movie.data.bean.RegisterBean;
import com.bw.movie.di.contract.RegisterContract;
import com.bw.movie.di.model.RegisterModel;

import java.lang.ref.SoftReference;

/**
 * 张娜
 * 注册
 */
public class RegisterPresenter implements RegisterContract.Presenter {

    RegisterContract.View view;
    private SoftReference<RegisterContract.View> reference;
    private RegisterModel model;

    @Override
    public void attachView(RegisterContract.View view) {
        this.view = view;
        reference = new SoftReference<>(view);
        model = new RegisterModel();
    }

    @Override
    public void dattachView(RegisterContract.View view) {
        reference.clear();
    }

    @Override
    public void requestData(String names, String phones, String encrypt, String s, int xb,
                            String dates, String emails) {
        model.reponseData(names, phones, encrypt, s, xb, dates, emails, new RegisterContract.Model.CallBack() {
            @Override
            public void oncall(RegisterBean bean) {
                view.showData(bean);
            }
        });
    }
}
