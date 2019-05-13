package com.bw.movie.ui.wdactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.data.bean.UpdatePwdBean;
import com.bw.movie.data.utils.EncryptUtil;
import com.bw.movie.di.contract.CzpwdContract;
import com.bw.movie.di.presenter.CzpwdPresenter;
import com.bw.movie.ui.activity.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 重置密码
 * 张娜
 */
public class CzpwdActivity extends AppCompatActivity implements CzpwdContract.View {

    @BindView(R.id.pwd1)
    TextView pwd1;
    @BindView(R.id.pwd2)
    EditText pwd2;
    @BindView(R.id.pwd3)
    EditText pwd3;
    @BindView(R.id.img)
    ImageView img;
    private CzpwdPresenter presenter;
    private int userid;
    private String sessionid;
    private String passsp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_czpwd);
        ButterKnife.bind(this);

        SharedPreferences m = getSharedPreferences("m", 0);
        userid = m.getInt("userid", 0);
        sessionid = m.getString("sessionid", "");
        passsp = m.getString("passsp", "");

        presenter = new CzpwdPresenter();
        presenter.attachView(this);

        //密码
        pwd1.setText(passsp + "");


        //返回
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = pwd2.getText().toString();
                String s2 = pwd3.getText().toString();
                String encrypt1 = EncryptUtil.encrypt(passsp);
                String encrypt2 = EncryptUtil.encrypt(s1);
                String encrypt3 = EncryptUtil.encrypt(s2);
                presenter.requestData(userid, sessionid, encrypt1, encrypt2, encrypt3);
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dattachView(this);
    }

    @Override
    public void showData(UpdatePwdBean bean) {
        String message = bean.getMessage();
        if (message.equals("密码修改成功")) {
            Toast.makeText(this, "您的密码 改变了 请重新登录", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(CzpwdActivity.this, LoginActivity.class));
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }
}
