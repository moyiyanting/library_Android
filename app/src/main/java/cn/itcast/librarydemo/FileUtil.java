package cn.itcast.librarydemo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 企业认证中的图片管理
 * 图片文件操作
 */
public class FileUtil {

    /**
     * 将Bitmap 图片保存到本地路径，并返回路径
     *
     * @param fileName 文件名称
     * @param bitmap   图片
     * @param资源类型，参照 MultimediaContentType 枚举，根据此类型，保存时可自动归类
     */
    public static String saveFile(Context c, String fileName, Bitmap bitmap) {
        return saveFile(c, "", fileName, bitmap);
    }

    public static String saveFile(Context c, String filePath, String fileName, Bitmap bitmap) {
        byte[] bytes=null;
        if(bitmap!=null){
            bytes = bitmapToBytes(bitmap);
        }else {
            Toast.makeText(c,"bitmap错误！", Toast.LENGTH_SHORT).show();
        }
        return saveFile(c, filePath, fileName, bytes);
    }

    /**
     * Bitmap 转 字节数组
     *
     * @param bm
     * @return
     */
    public static byte[] bitmapToBytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }

    public static String saveFile(Context c, String filePath, String fileName, byte[] bytes) {
        String fileFullName = "";
        FileOutputStream fos = null;
        File fullFile=null;
        File file = null;
        String dateFolder = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA).format(new Date());
        try {
            String suffix = "";
            if (filePath == null || filePath.trim().length() == 0) {
                //   filePath = Environment.getExternalStorageState() + "/Pictures/" + dateFolder + "/";       //这个弃用。不适合新版本手机
                //   filePath = c.getExternalFilesDir(Environment.DIRECTORY_PICTURES) + "/picturess/" ;          //放包内存图片
                Log.i("TAG", "saveFile: "+filePath);
                filePath = Environment.getExternalStorageDirectory() + "/picturess/" ;   //暂时不想加这个文件夹，直接在目录下存图片
            }
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            fullFile = new File(filePath, fileName + suffix);
            fileFullName = fullFile.getPath();
            fos = new FileOutputStream(new File(filePath, fileName + suffix));
            fos.write(bytes);
        } catch (Exception e) {
            fileFullName = "";
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    fileFullName = "";
                }
            }
        }
  /*      // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(c.getContentResolver(), fileFullName, fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 发送广播，通知刷新图库的显示
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + fullFile));
        intent.setPackage(c.getPackageName());
        c.sendBroadcast(intent);*/
        //Toast.makeText(c,"保存成功！",Toast.LENGTH_SHORT).show();
        return fileFullName;
    }

    /**
     *将字符串转换成Bitmap类型
     * @param string
     * @return
     */
    public static Bitmap stringtoBitmap(String string) {
        //将字符串转换成Bitmap类型
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(string, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }


    //=========================================== 保存图片，且通知到图库 ==========================================================
    /**
     * 将Bitmap 图片保存到本地路径，并返回路径
     *
     * @param fileName 文件名称
     * @param bitmap   图片
     * @param资源类型，参照 MultimediaContentType 枚举，根据此类型，保存时可自动归类
     */
    public static String saveFileInGallery(Context c, String fileName, Bitmap bitmap) {
        return saveFileInGallery(c, "", fileName, bitmap);
    }

    public static String saveFileInGallery(Context c, String filePath, String fileName, Bitmap bitmap) {
        byte[] bytes=null;
        if(bitmap!=null){
            bytes = bitmapToBytesInGallery(bitmap);
        }else {
            Toast.makeText(c,"bitmap错误！", Toast.LENGTH_SHORT).show();
        }
        return saveFileInGallery(c, filePath, fileName, bytes);
    }

    /**
     * Bitmap 转 字节数组
     *
     * @param bm
     * @return
     */
    public static byte[] bitmapToBytesInGallery(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }

    public static String saveFileInGallery(Context c, String filePath, String fileName, byte[] bytes) {
        String fileFullName = "";
        FileOutputStream fos = null;
        String dateFolder = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA).format(new Date());
        try {
            String suffix = "";
            if (filePath == null || filePath.trim().length() == 0) {
                //   filePath = Environment.getExternalStorageState() + "/Pictures/" + dateFolder + "/";       //这个弃用。不适合新版本手机
                filePath = c.getExternalFilesDir(Environment.DIRECTORY_PICTURES) + "/picturess/" ;          //放包内存图片
                Log.i("TAG", "saveFile: "+filePath);
                //    filePath = Environment.getExternalStorageDirectory() + "/picturess/" ;   暂时不想加这个文件夹，直接在目录下存图片
            }
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            File fullFile = new File(filePath, fileName + suffix);
            fileFullName = fullFile.getPath();
            fos = new FileOutputStream(new File(filePath, fileName + suffix));
            fos.write(bytes);

            // 其次把文件插入到系统图库
            try {
                MediaStore.Images.Media.insertImage(c.getContentResolver(), fullFile.getAbsolutePath(), fileName, null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            // 发送广播，通知刷新图库的显示
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + fullFile));
            intent.setPackage(c.getPackageName());
            c.sendBroadcast(intent);

        } catch (Exception e) {
            fileFullName = "";
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    fileFullName = "";
                }
            }
        }
        Toast.makeText(c,"保存成功！", Toast.LENGTH_SHORT).show();
        return fileFullName;
    }
}

