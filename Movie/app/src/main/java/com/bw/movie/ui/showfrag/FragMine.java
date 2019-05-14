package com.bw.movie.ui.showfrag;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.data.bean.MyMessageBean;
import com.bw.movie.data.bean.NewVerSessionBean;
import com.bw.movie.data.bean.QdBean;
import com.bw.movie.di.contract.MyContract;
import com.bw.movie.di.presenter.MyPresenter;
import com.bw.movie.ui.activity.LoginActivity;
import com.bw.movie.ui.wdactivity.MessageFkActivity;
import com.bw.movie.ui.wdactivity.WdgzActivity;
import com.bw.movie.ui.wdactivity.XtxxActivity;
import com.bw.movie.ui.wdactivity.YhxxActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 我的
 */
public class FragMine extends Fragment implements MyContract.View {
    @BindView(R.id.yl)
    ImageView yl;
    @BindView(R.id.img)
    SimpleDraweeView img;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.qd)
    Button qd;
    @BindView(R.id.wdxx)
    LinearLayout wdxx;
    @BindView(R.id.wdgz)
    LinearLayout wdgz;
    @BindView(R.id.gpjl)
    LinearLayout gpjl;
    @BindView(R.id.yjfk)
    LinearLayout yjfk;
    @BindView(R.id.zxbb)
    LinearLayout zxbb;
    @BindView(R.id.tcdl)
    LinearLayout tcdl;
    Unbinder unbinder;
    private MyPresenter presenter;
    private int userid;
    private String sessionid;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_mine, null);
        unbinder = ButterKnife.bind(this, view);

        SharedPreferences m = getActivity().getSharedPreferences("m", 0);
        userid = m.getInt("userid", 0);
        sessionid = m.getString("sessionid", "");

        // p
        presenter = new MyPresenter();
        presenter.attachView(this);
        presenter.requestData(userid, sessionid);


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.dattachView(this);
    }

    @OnClick({R.id.yl, R.id.qd, R.id.wdxx, R.id.wdgz, R.id.gpjl, R.id.yjfk, R.id.zxbb, R.id.tcdl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.yl:

                //系统消息
                startActivity(new Intent(getActivity(), XtxxActivity.class));

                break;
            case R.id.qd:

                //签到
                presenter.requestData3(userid, sessionid);

                break;
            case R.id.wdxx:

                //我的信息
                startActivity(new Intent(getActivity(), YhxxActivity.class));


                break;
            case R.id.wdgz:

                //我的关注
                startActivity(new Intent(getActivity(), WdgzActivity.class));

                break;
            case R.id.gpjl:

                //购票记录

                break;
            case R.id.yjfk:

                //意见反馈
                startActivity(new Intent(getActivity(), MessageFkActivity.class));

                break;
            case R.id.zxbb:

                //最新版本
                presenter.requestData4(userid, sessionid);

                break;
            case R.id.tcdl:

                //退出登录
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();

                break;
        }
    }


    //个人资料
    @Override
    public void showData(MyMessageBean bean) {
        MyMessageBean.ResultBean result = bean.getResult();
        //头像 昵称
        String headPic = result.getHeadPic();
        img.setImageURI(Uri.parse(headPic));
        String nickName = result.getNickName();
        name.setText(nickName);
    }

    //签到
    @Override
    public void showData2(QdBean bean) {
        String message = bean.getMessage();
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    //查询新版本
    @Override
    public void showData3(NewVerSessionBean bean) {
        int flag = bean.getFlag();
        if (flag == 1) {
            Toast.makeText(getActivity(), "有新版本,需要更新", Toast.LENGTH_SHORT).show();
        } else if (flag == 2) {
            Toast.makeText(getActivity(), "没新版本,不需要更新", Toast.LENGTH_SHORT).show();
        }
    }


}
