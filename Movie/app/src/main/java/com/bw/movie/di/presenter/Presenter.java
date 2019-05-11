package com.bw.movie.di.presenter;

import com.bw.movie.data.bean.LoginBean;
import com.bw.movie.di.contract.Contract;
import com.bw.movie.di.model.Model;

import java.lang.ref.SoftReference;

/**
 * 张娜
 * 登录
 * mvp p
 */
public class Presenter implements Contract.Presenter {

    Contract.View view;
    private SoftReference<Contract.View> reference;
    private Model model;

    @Override
    public void attachView(Contract.View view) {
        this.view=view;
        reference = new SoftReference<>(view);
        model = new Model();
    }

    @Override
    public void dattachView(Contract.View view) {
        reference.clear();
    }

    @Override
    public void requestData(String phones, String texts) {
        model.reponseData(phones,texts,new Contract.Model.CallBack() {
            @Override
            public void oncall(LoginBean bean) {
                view.showData(bean);
            }
        });
    }
}
