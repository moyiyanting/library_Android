package cn.itcast.librarydemo.util;

import android.accounts.NetworkErrorException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetUtils {

    /**
     * post请求
     * @param url
     * @return
     */
    public static String get(String url) {
        HttpURLConnection conn = null;
        try {
            //创建Url并且通过HttpURLConnection打开连接
            URL mURL=new URL(url);
            conn= (HttpURLConnection) mURL.openConnection();
            conn.setRequestMethod("GET");// 设置请求方法为post
            conn.setReadTimeout(5000);// 设置读取超时为5秒
            conn.setConnectTimeout(10000);// 设置连接网络超时为10秒

            //如果连接成功
            int responseCode=conn.getResponseCode();
            if(responseCode==200){
                //获取数据
                InputStream is=conn.getInputStream();
                String response=getStringFromInputStream(is);
                return response;
            }else{
                //如果连接失败
               throw new NetworkErrorException("response status is "+responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //最终执行语句，关闭连接
            if(conn!=null){
                conn.disconnect();
            }
        }
        return null;
    }


    private static String getStringFromInputStream(InputStream is) throws IOException {
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        byte[] buffer=new byte[1024];
        int len=-1;
        while((len=is.read(buffer))!=-1){
            os.write(buffer, 0, len);
        }
        is.close();
        String state=os.toString(); //把字节流转字符串
        os.close();
        return state;
    }
}