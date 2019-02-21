package com.example.weiducinema.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.bw.movie.R;
import com.example.weiducinema.activity.WDLoginActivity;
import com.example.weiducinema.activity.WDShowActivity;
import com.example.weiducinema.activity.my.MyAttentionActivity;
import com.example.weiducinema.activity.my.My_Message_Activity;
import com.example.weiducinema.activity.my.MyNewVersionsActivity;
import com.example.weiducinema.activity.my.MyOpinionActivity;
import com.example.weiducinema.activity.my.MyTrackerActivity;
import com.example.weiducinema.activity.my.SystemsActivity;
import com.example.weiducinema.base.DataCall;
import com.example.weiducinema.base.WDBaseFragment;

import com.example.weiducinema.bean.Result;
import com.example.weiducinema.bean.encrypt.UserInfo;
import com.example.weiducinema.bean.encrypt.UserInfoBean;
import com.example.weiducinema.core.exception.ApiException;
import com.example.weiducinema.core.http.DownLoadService;
import com.example.weiducinema.db.DBManager;
import com.example.weiducinema.precener.HeardPicPersent;
import com.example.weiducinema.precener.NewPersent;
import com.example.weiducinema.precener.SignPersent;
import com.facebook.drawee.view.SimpleDraweeView;
import com.j256.ormlite.dao.Dao;

import java.io.File;
import java.util.List;

import static android.app.Activity.RESULT_OK;


/**
 * Created by 邵新轩(轩少)
 * function:
 * on 2019/1/23
 */
public class WDMyFragment extends WDBaseFragment implements View.OnClickListener {
    private SimpleDraweeView my_pic;
    private TextView my_name;
    private Button my_sign;
    private LinearLayout my_message;
    private LinearLayout my_attention;
    private LinearLayout my_goupiao;
    private LinearLayout my_tickling;
    private LinearLayout my_new_versions;
    private LinearLayout my_finish;
    private DBManager manager;
    private List<UserInfo> student;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    private View diogView;
    private Dao<UserInfo, String> userDao;
    private SignPersent persent;
    private UserInfoBean userInfo1;
    private ImageView systems_message;
    private Button btn_paizhao;
    private Button btn_xiangce;
    private HeardPicPersent heardPicPersent;
    private String path = Environment.getExternalStorageDirectory() + "/head.jpg";
    private String url;
    private NewPersent newpersent;
    private String versionCode;
    private int userId;
    private String sessionId;

    @Override
    public String getPageName() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_my_layout;
    }

    @Override
    protected void initView(View view) {
        my_pic = view.findViewById(R.id.my_pic);
        my_name = view.findViewById(R.id.my_name);
        my_sign = view.findViewById(R.id.my_sign);
        my_message = view.findViewById(R.id.my_message);
        my_attention = view.findViewById(R.id.my_attention);
        my_goupiao = view.findViewById(R.id.my_goupiao);
        my_tickling = view.findViewById(R.id.my_tickling);
        my_new_versions = view.findViewById(R.id.my_new_versions);
        my_finish = view.findViewById(R.id.my_finish);
        systems_message = view.findViewById(R.id.systems_message);
        //添加点击事件
        my_pic.setOnClickListener(this);
        my_message.setOnClickListener(this);
        my_attention.setOnClickListener(this);
        my_goupiao.setOnClickListener(this);
        my_tickling.setOnClickListener(this);
        my_new_versions.setOnClickListener(this);
        my_finish.setOnClickListener(this);
        my_sign.setOnClickListener(this);
        systems_message.setOnClickListener(this);
        my_name.setOnClickListener(this);
        persent = new SignPersent(new SignCall());
        heardPicPersent = new HeardPicPersent(new HeardCall());
        newpersent = new NewPersent(new NewCall());
        try {
            versionCode = getActivity().getPackageManager().
                    getPackageInfo(getContext().getPackageName(), 0).versionName;
            try {
                if (student.size()!=0) {
                    UserInfo info = student.get(0);
                     userId = info.getUserId();
                     sessionId = info.getSessionId();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 89) {
            Crop(Uri.fromFile(new File(path)));
        }
        if (requestCode == 99&& resultCode == RESULT_OK) {

            Crop(data.getData());
        }
        if (requestCode == 100&& resultCode == RESULT_OK) {
            my_pic.setImageBitmap((Bitmap) data.getParcelableExtra("data"));
            //  heardPicPersent.reqeust(student.get(0).getUserId(),student.get(0).getSessionId(),);
        }
    }

    public static String getVersionName(Context context) throws PackageManager.NameNotFoundException {
        // 获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        String version = packInfo.versionName;
        return version;
    }
    @Override
    public void onResume() {
        super.onResume();
        //数据库查询
        try {
            userDao = DBManager.getInstance(getActivity()).getUserDao();
            student = userDao.queryForAll();
            if(student.size()==0) {
                Toast.makeText(getActivity(),"没有信息"+student,Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(getActivity(),"有信息"+student,Toast.LENGTH_SHORT).show();
                System.out.println("有信息"+student);
                UserInfo userInfo = student.get(0);
                userInfo1 = userInfo.getUserInfo();
                String pic = userInfo1.getHeadPic();
                my_pic.setImageURI(pic);
                String nickName = userInfo1.getNickName();
                my_name.setText(nickName);
                String sign = userInfo1.getSign();
                //签到
                if (sign.equals("1")){
                    my_sign.setText("签到");
                }else if (sign.equals("2")){
                    my_sign.setText("已签到");
                }
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.my_pic:
                if (student.size()!= 0) {
                    creatediog1();
                }else {
                    Toast.makeText(getActivity(),"请先登录",Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.my_name:
                if (student.size() == 0) {
                    startActivity(new Intent(getActivity(), WDLoginActivity.class));
                }
                break;
            case R.id.my_message:
                    startActivity(new Intent(getActivity(), My_Message_Activity.class));
                break;
            case R.id.my_attention://我的关注
                startActivity(new Intent(getActivity(), MyAttentionActivity.class));
                break;
            case R.id.my_goupiao:
                startActivity(new Intent(getActivity(), MyTrackerActivity.class));
                break;
            case R.id.my_tickling:
                startActivity(new Intent(getActivity(), MyOpinionActivity.class));
                break;
            case R.id.my_new_versions:
                creatediog2();
                break;
            case R.id.my_sign:
                if (student.size()!=0) {
                    my_sign.setText("已签到");
                    persent.reqeust(student.get(0).getUserId()+"",student.get(0).getSessionId()+"");
                }else {
                    Toast.makeText(getActivity(),"请先登录",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.systems_message:
                startActivity(new Intent(getActivity(), SystemsActivity.class));
                break;
            case R.id.my_finish:
                if (student.size()!=0) {
                    creatediog();
                }
                break;
            case R.id.paizhao:
                try {//Manifest.permission.CAMERA
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path)));
                    startActivityForResult(intent, 89);
                    alertDialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.xiangce:
                Intent intent2 = new Intent(Intent.ACTION_PICK);
                intent2.setType("image/*");
                startActivityForResult(intent2, 99);
                alertDialog.dismiss();
                break;


        }
    }

    private void creatediog1() {
        // TODO Auto-generated method stub
        builder = new AlertDialog.Builder(getActivity());
        alertDialog = builder.create();
        alertDialog.setTitle("请选择");
        diogView = View.inflate(getActivity(), R.layout.dialog_layout, null);
        alertDialog.setView(diogView);
        btn_paizhao = (Button) diogView.findViewById(R.id.paizhao);
        btn_xiangce = (Button) diogView.findViewById(R.id.xiangce);
        btn_paizhao.setOnClickListener(this);
        btn_xiangce.setOnClickListener(this);
        alertDialog.show();

    }


    private void creatediog(){
        // TODO Auto-generated method stub
        builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("提示").setIcon(R.drawable.my_pic_img);
        builder.setMessage("确定退出登陆")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                            try {
                                userDao.deleteBuilder().delete();//删除全部数据
                                //userDao.notifyChanges();
                                student.clear();//清空对象
                                my_name.setText("未登录");
                                my_pic.setImageResource(R.drawable.my_pic_img);
                                my_sign.setText("签到");
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }

                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                builder.create().dismiss();
            }
        });
        builder.create().show();
    }

    private void creatediog2(){
        // TODO Auto-generated method stub
        builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("检查更新").setIcon(R.drawable.my_pic_img);
        builder.setMessage("发现新版本")
                .setPositiveButton("下载", new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        newpersent.reqeust(userId,sessionId,versionCode);



                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                builder.create().dismiss();
            }
        });
        builder.create().show();
    }

    private void Crop(Uri data) {
        // TODO Auto-generated method stub
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(data, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 251);
        intent.putExtra("outputY", 251);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 100);

    }
    //获取数据
    class SignCall implements DataCall<Result<UserInfo>> {

        @Override
        public void success(Result<UserInfo> data) {
         userInfo1.setSign("2");
         Toast.makeText(getContext(), data.getMessage(), Toast.LENGTH_SHORT).show();

        }

        @Override
        public void fail(ApiException e) {
            Toast.makeText(getContext(), "异常", Toast.LENGTH_SHORT).show();
        }
    }
    //获取数据
    class HeardCall implements DataCall<Result> {

        @Override
        public void success(Result data) {

            Toast.makeText(getActivity(), data.getMessage(), Toast.LENGTH_SHORT).show();

        }

        @Override
        public void fail(ApiException e) {
            Toast.makeText(getContext(), "异常", Toast.LENGTH_SHORT).show();
        }
    }
    //获取数据
    class NewCall implements DataCall<Result> {

        @Override
        public void success(Result data) {
            url = data.getDownloadUrl();
            Intent intent=new Intent(getContext(),DownLoadService.class);
            intent.putExtra("download_url",url);
            getActivity().startService(intent);
            Toast.makeText(getActivity(), data.getMessage(), Toast.LENGTH_SHORT).show();

        }

        @Override
        public void fail(ApiException e) {
            Toast.makeText(getContext(), "异常", Toast.LENGTH_SHORT).show();
        }
    }
}
