package com.bw.movie.ui.wdactivity;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.data.bean.MyMessageBean;
import com.bw.movie.di.contract.YhxxContract;
import com.bw.movie.di.presenter.YhxxPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 用户信息
 */
public class YhxxActivity extends AppCompatActivity implements YhxxContract.View {

    @BindView(R.id.img)
    SimpleDraweeView img;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.sex)
    TextView sex;
    @BindView(R.id.birthday)
    TextView birthday;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.cz)
    ImageView cz;
    @BindView(R.id.back)
    ImageView back;
    private YhxxPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yhxx);
        ButterKnife.bind(this);

        SharedPreferences m = getSharedPreferences("m", 0);
        int userid = m.getInt("userid", 0);
        String sessionid = m.getString("sessionid", "");

        presenter = new YhxxPresenter();
        presenter.attachView(this);
        presenter.requestData(userid, sessionid);

    }

    @OnClick({R.id.cz, R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cz:

                //重置密码

                break;
            case R.id.back:

                //返回
                finish();

                break;
        }
    }

    @Override
    public void showData(MyMessageBean bean) {
        Log.d("YhxxActivity", bean.getMessage());
        MyMessageBean.ResultBean result = bean.getResult();
        String headPic = result.getHeadPic();
        img.setImageURI(Uri.parse(headPic));
        name.setText(result.getNickName());
        int sexs = result.getSex();
        if (sexs==1){
            sex.setText("男");
        }else if(sexs==2){
            sex.setText("女");
        }
        birthday.setText(result.getBirthday() + "");
        phone.setText(result.getPhone());
        email.setText(result.getEmail());
    }
}
