package com.changzhoureconfig.yimeng.com.reconfigapplication.utils;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.changzhoureconfig.yimeng.com.reconfigapplication.R;
import com.changzhoureconfig.yimeng.com.reconfigapplication.ui.views.dialog.TwoButtonStyleTwoDialog;

import java.io.File;
import java.util.Calendar;
import java.util.List;


/**
 * 工具类
 */
public class Tools {
    public static final String PHOTO_TYPE = "phototype";
    public static final int PHOTO_TYPE_MOOD = 68;
    public static final int PHOTO_TYPE_ARTICLE = 69;
    public static final int PHOTO_REQUEST_TAKEPHOTO = 11;// 拍照
    public static final int PHOTO_REQUEST_ONE_GALLERY = 12;// 从相册中选择
    public static final int PHOTO_REQUEST_MANY_GALLERY = 10; //从相册中多选发表文章
    public static final int PHOTO_REQUEST_MANY_GALLERY_ARTICLE_RESULT = 19; //从相册中多选发表文章结果码
    public static final int PHOTO_REQUEST_MANY_GALLERY_MOOD_RESULT = 29; //从相册中多选发表说说结果码
    private static final boolean DEBUG = true;
    private static final String TAG = "Tools";
    private static Toast mToast;
    private static File cameratempFile;

    /**
     * 通用toast.
     * 如果重复发出同一条msg，则将时间初始化，而不是重复弹出提示框
     */
    public static void showToast(Context context, String msg) {
        if (null == context) {
            return;
        }
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.toast_custom_layout, null);
        if (mToast == null) {
            mToast = new Toast(context.getApplicationContext());
        }
        TextView textView = (TextView) layout.findViewById(R.id.custom_toast);
        textView.setText(msg);
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setView(layout);
        mToast.show();
    }
    /**
     * 通用toast.
     * 如果重复发出同一条msg，则将时间初始化，而不是重复弹出提示框
     */
    public static void showHintToast(Context context, String msg) {
        if (null == context) {
            return;
        }
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.toast_hint_layout, null);
        if (mToast == null) {
            mToast = new Toast(context.getApplicationContext());
        }
        TextView textView = (TextView) layout.findViewById(R.id.hint_toast);
        textView.setText(msg);
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setView(layout);
        mToast.show();
    }
    /**
     * 通用toast.
     * 如果重复发出同一条msg，则将时间初始化，而不是重复弹出提示框
     */
    public static void showHint2Toast(Context context, String msg) {
        if (null == context) {
            return;
        }
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.toast_hint2_layout, null);
        if (mToast == null) {
            mToast = new Toast(context.getApplicationContext());
        }
        TextView textView = (TextView) layout.findViewById(R.id.hint2_toast);
        textView.setText(msg);
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setView(layout);
        mToast.show();
    }
    public static void showToastLong(Context context, String msg) {
        if (null == context) {
            return;
        }
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.toast_custom_layout, null);
        if (mToast == null) {
            mToast = new Toast(context.getApplicationContext());
        }
        TextView textView = (TextView) layout.findViewById(R.id.custom_toast);
        textView.setText(msg);
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.setDuration(Toast.LENGTH_LONG);
        mToast.setView(layout);
        mToast.show();
    }

    public static void showCameraAndChooseOneDialog(final Activity activity, final File tempFile) {
        final Dialog dialog;
        LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.photo_choose_dialog, null);
        Button photograph = (Button) view.findViewById(R.id.photograph);
        Button photo = (Button) view.findViewById(R.id.photo);
        Button call_off = (Button) view.findViewById(R.id.call_off);
        dialog = new Dialog(activity, R.style.transparentFrameWindowStyle);
        dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = activity.getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        dialog.onWindowAttributesChanged(wl);
        // 设置点击外围解散
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        int REQUEST_EXTERNAL_STORAGE = 1;
        String[] PERMISSIONS_STORAGE = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
        call_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dialog.cancel();
            }
        });
        photograph.setOnClickListener(new View.OnClickListener() {//拍照按钮
            @Override
            public void onClick(View arg0) {
                dialog.cancel();
                Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                tempFile.getParentFile().mkdirs();
                // 指定调用相机拍照后照片的储存路径
                cameraintent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
                activity.startActivityForResult(cameraintent, PHOTO_REQUEST_TAKEPHOTO);
            }
        });
        photo.setOnClickListener(new View.OnClickListener() {//从相册选择按钮
            @Override
            public void onClick(View arg0) {
                dialog.cancel();
                Intent intentFromGallery = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intentFromGallery.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                activity.startActivityForResult(intentFromGallery, PHOTO_REQUEST_ONE_GALLERY);
            }
        });
    }

    /**
     * 照相机的文件
     *
     * @return
     */
    public static File getCameratempFile() {
        return cameratempFile;
    }

    /**
     * @param activity
     */
    public static void showCameraAndChooseManyDialog(final Activity activity, final int type, final int onlychoose) {
        final Dialog dialog;
        LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.photo_choose_dialog, null);
        Button photograph = (Button) view.findViewById(R.id.photograph);
        Button photo = (Button) view.findViewById(R.id.photo);
        Button call_off = (Button) view.findViewById(R.id.call_off);
        dialog = new Dialog(activity, R.style.transparentFrameWindowStyle);
        dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = activity.getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        dialog.onWindowAttributesChanged(wl);
        // 设置点击外围解散
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        int REQUEST_EXTERNAL_STORAGE = 1;
        String[] PERMISSIONS_STORAGE = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
        call_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dialog.cancel();
            }
        });
        photograph.setOnClickListener(new View.OnClickListener() {//拍照按钮
            @Override
            public void onClick(View arg0) {
                dialog.cancel();
                Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // 指定调用相机拍照后照片的储存路径
                cameratempFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/" + BaseUtils.getPhotoFileName());
                cameratempFile.getParentFile().mkdirs();
                cameraintent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cameratempFile));
                if (type == PHOTO_TYPE_MOOD) {
                    cameraintent.putExtra(PHOTO_TYPE, PHOTO_TYPE_MOOD);
                } else if (type == PHOTO_TYPE_ARTICLE) {
                    cameraintent.putExtra(PHOTO_TYPE, PHOTO_TYPE_ARTICLE);
                }
                activity.startActivityForResult(cameraintent, PHOTO_REQUEST_TAKEPHOTO);
            }
        });

//        photo.setOnClickListener(new View.OnClickListener() {//从相册选择按钮
//            @Override
//            public void onClick(View arg0) {
//                dialog.cancel();
//                Intent albumIntent = new Intent(activity, AlbumActivity.class);
//                albumIntent.putExtra("onlychoose",onlychoose);
//                if (type == PHOTO_TYPE_MOOD) {
//                    albumIntent.putExtra(PHOTO_TYPE, PHOTO_TYPE_MOOD);
//                } else if (type == PHOTO_TYPE_ARTICLE) {
//                    albumIntent.putExtra(PHOTO_TYPE, PHOTO_TYPE_ARTICLE);
//                }
//                activity.startActivityForResult(albumIntent, PHOTO_REQUEST_MANY_GALLERY);
//            }
//        });
    }

    /**
     * URI转成file路径
     *
     * @param mContext
     * @param contentUri
     * @return
     */
    /**
     * 根据Uri获取图片文件的绝对路径
     */
    public static String getFilePathByUri(Context context, final Uri uri) {
        if (null == uri) {
            return null;
        }
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri,
                    new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    /**
     * 粉色图片toast.
     * 如果重复发出同一条msg，则将时间初始化，而不是重复弹出提示框
     */
    public static void showImgToast(Context context, String msg, int drawable, int bgres, int tvcoclor) {
        if (null == context) {
            return;
        }
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.toast_pink_custom_layout, null);
        if (mToast == null) {
            mToast = new Toast(context.getApplicationContext());
        }
        RelativeLayout imgtoastlayout = (RelativeLayout) layout.findViewById(R.id.imgtoastlayout);
        if (bgres != 0) {
            imgtoastlayout.setBackgroundResource(bgres);
        }
        ImageView toastimg = (ImageView) layout.findViewById(R.id.toastimg);
        toastimg.setImageResource(drawable);
        TextView toastinfo = (TextView) layout.findViewById(R.id.toastinfo);
        toastinfo.setText(msg);
        if (tvcoclor != 0) {
            toastinfo.setTextColor(tvcoclor);
        }
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setView(layout);
        mToast.show();
    }

    /**
     * 检测网络是否可用.
     */
    public static boolean isNetworkConnected(Context context) {
        boolean flag = false;
        if (null == context)
            return false;
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context.getApplicationContext()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity == null) {
                return false;
            }
            NetworkInfo networkInfo = connectivity.getActiveNetworkInfo();
            flag = networkInfo != null && networkInfo.isAvailable();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return flag;
    }

    /**
     * 通用dialog，一个键
     */
    public static void commonDialogOneBtn(Context context, String title, String content, String buttonName) {
        if (null == content) {
            return;
        }
        context.fileList();
        TwoButtonStyleTwoDialog.Builder alert = new TwoButtonStyleTwoDialog.Builder(context);
        alert.setTitle(title);
        alert.setMessage(content);
        alert.setPositiveButton(buttonName, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }

        });
        alert.create().show();
    }

    /**
     * 通用dialog，一个键
     */
    public static void commonDialogOneBtn(Context context, String title, String content, String buttonName, final Activity baseActivity) {
        if (null == content) {
            return;
        }
        context.fileList();
        TwoButtonStyleTwoDialog.Builder alert = new TwoButtonStyleTwoDialog.Builder(context);
        alert.setTitle(title);
        alert.setMessage(content);
        alert.setPositiveButton(buttonName, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                baseActivity.finish();
            }

        });
        Dialog dialog = alert.create();
        dialog.show();
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.width = BaseUtils.getScreenWidth(context) / 2;
        dialog.getWindow().setAttributes(layoutParams);
    }


    /**
     * make a aboutcall
     *
     * @param context
     */
    public static void makeCall(Context context, String cellphone) {
        try {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + cellphone));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            if (DEBUG) {
                Log.e(TAG, e.toString());
            }
            Tools.showHintToast(context, "抱歉，未找到打电话的应用");
        } catch (Exception e) {
            if (DEBUG) {
                Log.e(TAG, e.toString());
            }
        }
    }

    /**
     * 检查query是否是电话号码
     *
     * @return 是否是电话号码
     */
    public static boolean checkIsPhoneNumber(String num) {
        if (TextUtils.isEmpty(num)) {
            return false;
        }
        return CheckUtils.PHONE.matcher(num).matches();
    }

    /**
     * 获取当前版本
     */
    public static String getVersion(Context context) {
        String versionName = "0";
        try {
            PackageInfo manager = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            versionName = manager.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * jPush推送过来的字段判断
     *
     * @param s
     * @return
     */
    public static boolean isEmpty(String s) {
        if (null == s)
            return true;
        if (s.length() == 0)
            return true;
        if (s.trim().length() == 0)
            return true;
        return false;
    }

    /**
     * 判断 用户是否安装QQ客户端
     */
    public static boolean isQQClientAvailable(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equalsIgnoreCase("com.tencent.qqlite") || pn.equalsIgnoreCase("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @return
     */
    public static int daysBetween(Calendar c1, Calendar c2) {
        long time1 = c1.getTimeInMillis();
        long time2 = c2.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }
}
