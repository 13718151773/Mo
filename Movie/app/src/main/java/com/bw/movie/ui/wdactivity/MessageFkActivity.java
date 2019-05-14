package com.bw.movie.ui.wdactivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.data.bean.MessageFkBean;
import com.bw.movie.di.contract.MessageFkContract;
import com.bw.movie.di.presenter.MessageFkPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 意见反馈
 * 张娜
 */
public class MessageFkActivity extends AppCompatActivity implements MessageFkContract.View {

    @BindView(R.id.ed)
    EditText ed;
    @BindView(R.id.tj)
    Button tj;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.lin1)
    LinearLayout lin1;
    @BindView(R.id.lin2)
    LinearLayout lin2;
    private MessageFkPresenter presenter;
    private int userid;
    private String sessionid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_fk);
        ButterKnife.bind(this);

        SharedPreferences m = getSharedPreferences("m", 0);
        userid = m.getInt("userid", 0);
        sessionid = m.getString("sessionid", "");

        presenter = new MessageFkPresenter();
        presenter.attachView(this);

    }

    @OnClick({R.id.tj, R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tj:

                //提交
                String s = ed.getText().toString();
                presenter.requestData(userid, sessionid, s);

                break;
            case R.id.back:

                //返回
                finish();

                break;
        }
    }

    @Override
    public void showData(MessageFkBean bean) {
        String message = bean.getMessage();
        if (message.equals("反馈成功")) {
            lin1.setVisibility(View.GONE);
            lin2.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dattachView(this);
    }
}
