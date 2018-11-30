package com.bw.movie.view.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.model.bean.UpLoadHeadPicBean;
import com.bw.movie.model.net.ImageUtils;
import com.bw.movie.presenter.UpLoadHeadPicPresenter;
import com.bw.movie.view.iview.UpLoadHearPic;
import com.facebook.drawee.view.SimpleDraweeView;
import com.bw.movie.R;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.internal.Utils;

public class MineInfoActivity extends AppCompatActivity implements UpLoadHearPic {

    @BindView(R.id.simp_myinfohead)
    SimpleDraweeView simpMyinfohead;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_birthdate)
    TextView tvBirthdate;
    @BindView(R.id.tv_telephone)
    TextView tvTelephone;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.img_resetpwd)
    ImageView imgResetpwd;
    @BindView(R.id.img_myinfoback)
    ImageView imgMyinfoback;
    @BindView(R.id.btn_logout)
    Button btnLogout;
    private SharedPreferences sp;
    private SharedPreferences sp1;
    private boolean isLogin;

    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    protected static Uri tempUri;
    private ImageView iv_personal_icon;
    private UpLoadHeadPicPresenter upLoadHeadPicPresenter;
    private SharedPreferences user;
    private String userId;
    private String sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_info);
        ButterKnife.bind(this);
        upLoadHeadPicPresenter = new UpLoadHeadPicPresenter();
        upLoadHeadPicPresenter.attachView(this);
        user = MineInfoActivity.this.getSharedPreferences("user", MODE_PRIVATE);
        userId = user.getString("userId", "");
        sessionId = user.getString("sessionId", "");
        Log.e("userid111", "onCreate: "+userId );
    }

    @OnClick(R.id.img_myinfoback)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sp = getSharedPreferences("user", Context.MODE_PRIVATE);
        isLogin = sp.getBoolean("isLogin", false);
        if (isLogin) {
            String touicon = sp.getString("touicon", "");
            String username = sp.getString("username", "");
            String phone = sp.getString("phone", "");
            int gender = sp.getInt("gender", 0);
            if (gender == 1) {
                tvSex.setText("男");
            } else if (gender == 2) {
                tvSex.setText("女");
            }
            simpMyinfohead.setImageURI(Uri.parse(touicon));
            tvUsername.setText(username);
            tvTelephone.setText(phone);
        }
    }

    @OnClick({R.id.simp_myinfohead, R.id.img_resetpwd, R.id.btn_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.simp_myinfohead:
                showChoosePicDialog();
                break;
            case R.id.img_resetpwd:
                break;
            case R.id.btn_logout:
                sp1 = getSharedPreferences("user", MODE_PRIVATE);
                if (isLogin){
                    SharedPreferences.Editor edit = sp1.edit();
                    edit.putBoolean("isLogin",false);
                }else {
                    Toast.makeText(this,"请先登录",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /**
     * 显示修改头像的对话框
     */
    private void showChoosePicDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("设置头像");
        String[] items = { "选择本地照片", "拍照" };
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case CHOOSE_PICTURE: // 选择本地照片
                        Intent openAlbumIntent = new Intent(
                                Intent.ACTION_GET_CONTENT);
                        openAlbumIntent.setType("image/*");
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                        break;
                    case TAKE_PICTURE: // 拍照
                        takePicture();
                        break;
                }
            }
        });
        builder.create().show();
    }

    private void takePicture() {
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (Build.VERSION.SDK_INT >= 23) {
            // 需要申请动态权限
            int check = ContextCompat.checkSelfPermission(this, permissions[0]);
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (check != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
        Intent openCameraIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment
                .getExternalStorageDirectory(), "image.jpg");
        //判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= 24) {
            openCameraIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            tempUri = FileProvider.getUriForFile(MineInfoActivity.this, "com.lt.uploadpicdemo.fileProvider", file);
        } else {
            tempUri = Uri.fromFile(new File(Environment
                    .getExternalStorageDirectory(), "image.jpg"));
        }
        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
        startActivityForResult(openCameraIntent, TAKE_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PICTURE:
                    startPhotoZoom(tempUri); // 开始对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
            }
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    protected void startPhotoZoom(Uri uri) {
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }


    /**
     * 保存裁剪之后的图片数据
     *
     * @param
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            Log.d("photo","setImageToView:"+photo);
            photo = ImageUtils.toRoundBitmap(photo,tempUri); // 这个时候的图片已经被处理成圆形的了
            uploadPic(photo);
        }
    }


    private void uploadPic(Bitmap bitmap) {
        // 上传至服务器
        // ... 可以在这里把Bitmap转换成file，然后得到file的url，做文件上传操作
        // 注意这里得到的图片已经是圆形图片了
        // bitmap是没有做个圆形处理的，但已经被裁剪了
        String imagePath = ImageUtils.savePhoto(bitmap, Environment
                .getExternalStorageDirectory().getAbsolutePath(), String
                .valueOf(System.currentTimeMillis()));
        Log.e("imagePath", imagePath+"");
        if(imagePath != null){
            upLoadHeadPicPresenter.getUpLoadHearPic(userId,sessionId,new File(imagePath));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

        } else {
            // 没有获取 到权限，从新请求，或者关闭app
            Toast.makeText(this, "需要存储权限", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void upLoadHeadPicSuccess(UpLoadHeadPicBean upLoadHeadPicBean) {
        String headPath = upLoadHeadPicBean.getHeadPath();
        Log.e("headPath", "upLoadHeadPicSuccess: "+headPath );
        String message = upLoadHeadPicBean.getMessage();
        String status = upLoadHeadPicBean.getStatus();
        if (status.equals("0000")){
            simpMyinfohead.setImageURI(headPath);
            Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void upLoadHeadPicError() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        upLoadHeadPicPresenter.detachView();
    }
}
