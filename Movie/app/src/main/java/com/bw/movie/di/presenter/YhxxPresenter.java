package com.bw.movie.di.presenter;

import com.bw.movie.data.bean.MyMessageBean;
import com.bw.movie.data.bean.SctxBean;
import com.bw.movie.di.contract.YhxxContract;
import com.bw.movie.di.model.YhxxModel;

import java.lang.ref.SoftReference;

import okhttp3.MultipartBody;

/***
 * 用户信息
 * p
 */
public class YhxxPresenter implements YhxxContract.Presenter {

    YhxxContract.View view;
    private SoftReference<YhxxContract.View> reference;
    private YhxxModel model;

    @Override
    public void attachView(YhxxContract.View view) {
        this.view=view;
        reference = new SoftReference<>(view);
        model = new YhxxModel();
    }

    @Override
    public void dattachView(YhxxContract.View view) {
        reference.clear();
    }

    @Override
    public void requestData(int userid, String sessionid) {
        model.reponseData(userid,sessionid,new YhxxContract.Model.CallBack() {
            @Override
            public void oncall(MyMessageBean bean) {
                view.showData(bean);
            }

            @Override
            public void oncall2(SctxBean bean) {

            }
        });
    }

    //上传头像
    @Override
    public void requestData2(int userid, String sessionid, MultipartBody.Part part) {
        model.reponseData2(userid,sessionid,part,new YhxxContract.Model.CallBack() {
            @Override
            public void oncall(MyMessageBean bean) {

            }

            @Override
            public void oncall2(SctxBean bean) {
                view.showData2(bean);
            }
        });
    }
}
