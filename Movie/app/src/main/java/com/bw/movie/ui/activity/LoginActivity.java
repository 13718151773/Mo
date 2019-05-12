package com.bw.movie.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.data.bean.LoginBean;
import com.bw.movie.data.utils.EncryptUtil;
import com.bw.movie.di.contract.Contract;
import com.bw.movie.di.presenter.Presenter;
import com.xw.repo.XEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * 张娜
 * 登录activity
 *
 */
public class LoginActivity extends AppCompatActivity implements Contract.View{

    @BindView(R.id.phone)
    XEditText phone;
    @BindView(R.id.pass)
    XEditText pass;
    @BindView(R.id.rmm)
    CheckBox rmm;
//    @BindView(R.id.zd)
//    CheckBox zd;
    @BindView(R.id.kszc)
    TextView kszc;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.wenxin)
    ImageView wenxin;
    private Presenter presenter;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        //注册页面回来的账号 密码
        Intent intent = getIntent();
        String phones = intent.getStringExtra("phone");
        String passs = intent.getStringExtra("pass");
        phone.setText(phones);
        pass.setText(passs);

        //sp
        sp = getSharedPreferences("m", 0);

        //自动登录
        boolean zd = sp.getBoolean("zd", false);
        if (zd){
            startActivity(new Intent(LoginActivity.this,ShowActivity.class));
        }else{

        }

        //记住密码
        boolean jz = sp.getBoolean("jz", false);
        if (jz){
            String spString = sp.getString("phonesp", "");
            String spString1 = sp.getString("passsp", "");
            phone.setText(spString+"");
            pass.setText(spString1+"");
            rmm.setChecked(jz);
        }else{

        }

        //p
        presenter = new Presenter();
        presenter.attachView(this);


    }

    @OnClick({R.id.rmm,  R.id.kszc, R.id.login, R.id.wenxin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rmm:

                //记住密码
                SharedPreferences.Editor edit = sp.edit();
                String s1 = phone.getText().toString();
                String s2 = pass.getText().toString();
                edit.putString("phonesp",s1);
                edit.putString("passsp",s2);
                if (rmm.isChecked()){
                    edit.putBoolean("jz",true);
                }else{
                    edit.putBoolean("jz",false);
                }
                edit.commit();

                break;
//            case R.id.zd:
//
//                //自动登录
////                SharedPreferences.Editor edit1 = sp.edit();
////                if (zd.isChecked()){
////                    edit1.putBoolean("zd",true);
////                }else{
////                    edit1.putBoolean("zd",false);
////                }
//
//
//                break;
            case R.id.kszc:

                //快速注册
                //跳转到登录页面
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));

                break;
            case R.id.login:

                //登录
                String phones = phone.getText().toString();
                String texts = pass.getText().toString();
                String s = EncryptUtil.encrypt(texts);
                presenter.requestData(phones,s);

                break;
            case R.id.wenxin:

                //微信图标
                Toast.makeText(this, "您点击了微信图标", Toast.LENGTH_SHORT).show();

                break;
        }
    }

    //数据
    @Override
    public void showData(LoginBean bean) {
        String message = bean.getMessage();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        if (message.equals("登陆成功")){
            int userId = bean.getResult().getUserId();
            String sessionId = bean.getResult().getSessionId();
            SharedPreferences.Editor edit = sp.edit();
            edit.putInt("userid",userId);
            edit.putString("sessionid",sessionId);
            edit.commit();
            startActivity(new Intent(LoginActivity.this,ShowActivity.class));
        }else{

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dattachView(this);
    }
}
