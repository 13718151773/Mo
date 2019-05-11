package com.bw.movie.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.bw.movie.R;
import com.bw.movie.data.bean.RegisterBean;
import com.bw.movie.data.utils.EncryptUtil;
import com.bw.movie.di.contract.RegisterContract;
import com.bw.movie.di.presenter.RegisterPresenter;
import com.xw.repo.XEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 张娜 注册
 */
public class RegisterActivity extends AppCompatActivity implements RegisterContract.View {

    @BindView(R.id.name)
    XEditText name;
    @BindView(R.id.sex)
    XEditText sex;
    @BindView(R.id.date)
    XEditText date;
    @BindView(R.id.email)
    XEditText email;
    @BindView(R.id.pass)
    XEditText pass;
    @BindView(R.id.register)
    Button register;
    @BindView(R.id.phone1)
    XEditText phone;
    private RegisterPresenter presenter;
    int xb = 0;
    int[] a={4,2,2};
    private String phones;
    private String passs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        date.setSeparator("-");
        date.setPattern(a);


        //p
        presenter = new RegisterPresenter();
        presenter.attachView(this);

    }

    @OnClick(R.id.register)
    public void onViewClicked() {
        // 昵称 手机号 密码 重复密码 性别（1 男） 出生日期
        //移动设备唯一识别码 设备类型 屏幕尺寸 手机系统 邮箱
        String names = name.getText().toString();
        String sexs = sex.getText().toString();
        if (sexs.equals("男")) {
            xb = 1;
        } else {
            xb = 2;
        }
        String dates = date.getText().toString();
        phones = phone.getText().toString();
        String emails = email.getText().toString();
        passs = pass.getText().toString();
        //加密
        String encrypt = EncryptUtil.encrypt(passs);

        presenter.requestData(names, phones,encrypt,encrypt,xb,dates,emails);
    }

    @Override
    public void showData(RegisterBean bean) {
        String message = bean.getMessage();
        Log.d("RegisterModel", message);
        if (message.equals("注册成功")){
            //回去登录页面
            Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
            intent.putExtra("phone",phones);
            intent.putExtra("pass",passs);
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dattachView(this);
    }
}
