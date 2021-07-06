package cn.itcast.librarydemo.util;

import android.os.Handler;

import org.json.JSONException;

public class AsynNetUtils {
    /**
     * 实现回调接口
     */
    public interface Callback{
        void onResponse(String response) throws JSONException;
    }

    /**
     *
     * @param url
     * @param callback
     */
    public static void get(final String url,final Callback callback){
        final Handler handler=new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //通过NetUtils获取url数据
                final String response=NetUtils.get(url);
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            callback.onResponse(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        }).start();
    }

}
