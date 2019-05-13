package com.bw.movie.ui.wdactivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.data.bean.MyMessageBean;
import com.bw.movie.data.bean.SctxBean;
import com.bw.movie.data.utils.TimeUtils;
import com.bw.movie.di.contract.YhxxContract;
import com.bw.movie.di.presenter.YhxxPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 用户信息
 */
public class YhxxActivity extends AppCompatActivity implements YhxxContract.View, View.OnClickListener {

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
    @BindView(R.id.rmain)
    LinearLayout rmain;
    private YhxxPresenter presenter;
    private View view;
    private PopupWindow myPop;
    //相机相册
    private String icon = "com.bw.xiangji";
    //相册请求码
    private static final int ALBUM_REQUEST_CODE = 1;
    //相机请求码
    private static final int CAMERA_REQUEST_CODE = 2;
    //剪裁请求码
    private static final int CROP_REQUEST_CODE = 3;
    //调用照相机返回图片文件
    private File tempFile;
    private int userid;
    private String sessionid;
    private File file;
    private String photoPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yhxx);
        ButterKnife.bind(this);

        SharedPreferences m = getSharedPreferences("m", 0);
        userid = m.getInt("userid", 0);
        sessionid = m.getString("sessionid", "");

        presenter = new YhxxPresenter();
        presenter.attachView(this);
        presenter.requestData(userid, sessionid);

    }

    @OnClick({R.id.cz, R.id.back, R.id.img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img:

                //头像
                //上传头像
                Toast.makeText(this, "点击了", Toast.LENGTH_SHORT).show();
                showPicSelect();

                break;
            case R.id.cz:

                //重置密码
                startActivity(new Intent(YhxxActivity.this,CzpwdActivity.class));

                break;
            case R.id.back:

                //返回
                finish();

                break;
        }
    }

    //用户信息
    @Override
    public void showData(MyMessageBean bean) {
        Log.d("YhxxActivity", bean.getMessage());
        MyMessageBean.ResultBean result = bean.getResult();
        String headPic = result.getHeadPic();
        img.setImageURI(Uri.parse(headPic));
        name.setText(result.getNickName());
        int sexs = result.getSex();
        if (sexs == 1) {
            sex.setText("男");
        } else if (sexs == 2) {
            sex.setText("女");
        }
        long birthday = result.getBirthday();
        String s = TimeUtils.longToDate(birthday);
        this.birthday.setText(s + "");
        phone.setText(result.getPhone());
        email.setText(result.getEmail());
    }

    //上传头像
    @Override
    public void showData2(SctxBean bean) {
        String message = bean.getMessage();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    //上传头像用的popwindow 1
    @SuppressLint("InflateParams")
    private void showPicSelect() {
        view = LayoutInflater.from(YhxxActivity.this).inflate(R.layout.popwindow, null, false);
        LinearLayout llPop = view.findViewById(R.id.ll_pic);
        Button btnCamera = view.findViewById(R.id.btn_pic_camera);
        Button btnPhoto = view.findViewById(R.id.btn_pic_photo);
        Button btnCancel = view.findViewById(R.id.btn_pic_cancel);

        btnCamera.setOnClickListener(this);
        btnPhoto.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        llPop.setOnClickListener(this);

        myPop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        myPop.setBackgroundDrawable(new ColorDrawable());
        myPop.showAtLocation(rmain, Gravity.BOTTOM, 0, 0);
    }

    //上传头像用的popwindow 2
    @Override
    public void onBackPressed() {
        if (myPop.isShowing()) {
            myPop.dismiss();
        } else {

        }
    }


    //上传头像用的popwindow 3
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pic_camera:

                //拍照
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    applyWritePermission();
                } else {
                    jsPath();
                }
                myPop.dismiss();

                break;
            case R.id.btn_pic_photo:

                //相册
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, ALBUM_REQUEST_CODE);
                myPop.dismiss();

                break;
            case R.id.btn_pic_cancel:

                //取消
                myPop.dismiss();

                break;
        }
    }

    //回调方法
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            // 调用相机后返回
            case CAMERA_REQUEST_CODE:
                if (requestCode == 1 && resultCode == RESULT_OK) {
                    //在手机相册中显示刚拍摄的图片
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                        Uri contentUri = Uri.fromFile(file);
                        String path = contentUri.getPath();
                        //文件的路径
                        String absolutePath = file.getAbsolutePath();
                        //文件的名字
                        String parent = file.getName();
                        mediaScanIntent.setData(contentUri);
                        sendBroadcast(mediaScanIntent);
                        //设置图片
                        img.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
                        sc(file);
                    } else {
                        File photoFile = new File(photoPath);
                        if (photoFile.exists()) {
                            //通过图片地址将图片加载到bitmap里面
                            Bitmap bm = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                            //将拍摄的照片显示到界面上
                            //这是返回来的路径
                            Toast.makeText(YhxxActivity.this, "通过", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(YhxxActivity.this, "图片文件不存在", Toast.LENGTH_LONG).show();
                        }
                        sc(photoFile);
                    }
                }
                break;
            //调用相册后返回
            case ALBUM_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    cropPhoto(uri);//裁剪图片
                }
                break;
            //调用剪裁后返回
            case CROP_REQUEST_CODE:
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    //在这里获得了剪裁后的Bitmap对象，可以用于上传
                    Bitmap image = bundle.getParcelable("data");
                    //也可以进行一些保存、压缩等操作后上传
                    String path = saveImage("userHeader", image);
                    File file = new File(path);
                    sc(file);
                }
                break;
        }
    }


    /**
     * 拍照的，照片路径
     */
    public void jsPath() {
        //获取SD卡安装状态
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {

            //设置图片保存路径
            photoPath = Environment.getDownloadCacheDirectory() + "/" + System.currentTimeMillis() + ".png";

            File imageDir = new File(photoPath);
            if (!imageDir.exists()) {
                try {
                    //根据一个 文件地址生成一个新的文件用来存照片
                    imageDir.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            takePhotoByMethod1();
        } else {
            Toast.makeText(YhxxActivity.this, "SD卡未插入", Toast.LENGTH_SHORT).show();
        }
    }

    private void takePhotoByMethod1() {
        //实例化intent,指向摄像头
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //根据路径实例化图片文件
        File photoFile = new File(photoPath);
        //设置拍照后图片保存到文件中
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
        //启动拍照activity并获取返回数据
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }

    //权限
    public void applyWritePermission() {
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (Build.VERSION.SDK_INT >= 23) {
            int check = ContextCompat.checkSelfPermission(this, permissions[0]);
            if (check == PackageManager.PERMISSION_GRANTED) {
                //调用相机
                useCamera();
            } else {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        } else {
            useCamera();
        }
    }

    //权限的回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            useCamera();
        } else {
            // 没有获取 到权限，从新请求，或者关闭app
            Toast.makeText(this, "需要存储权限", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 从相机获取图片
     */
    private void useCamera() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/test/" + System.currentTimeMillis() + ".jpg");
        file.getParentFile().mkdirs();

        //改变Uri  com.xykj.customview.fileprovider注意和xml中的一致
        Uri uri = FileProvider.getUriForFile(this, "com.bw.movie.fileprovider", file);
        //添加权限
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }

    /**
     * 裁剪图片
     */
    private void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_REQUEST_CODE);
    }


    //上传图片
    private void sc(File file1) {
        RequestBody body = RequestBody.create(MediaType.parse("application/otcet-stream"), file1);
        //文件的key就是image
        MultipartBody.Part part = MultipartBody.Part.createFormData("image", file1.getName(), body);
        presenter.requestData2(userid, sessionid, part);
    }

    /**
     * 保存图片到本地
     */
    public String saveImage(String name, Bitmap bmp) {
        File appDir = new File(Environment.getExternalStorageDirectory().getPath());
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = name + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
