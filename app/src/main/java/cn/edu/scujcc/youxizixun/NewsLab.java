package cn.edu.scujcc.youxizixun;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NewsLab {
    //单例第1步
    private static NewsLab INSTANCE = null;

    private List<News> data;
    public final static int MSG_NEWS = 1;
    public final static int MSG_FALL = 4;

    //单例第2步
    private NewsLab() {
        //初始化空白列表
        data = new ArrayList<>();
        //删除网络访问
        //getData();
    }
    //单例第3步
    public static NewsLab getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NewsLab();
        }
        return INSTANCE;
    }

    /**
     * 返回数据总数量。
     *
     * @return
     */
    public int getSize() {
        return data.size();
    }

    /**
     * 返回指定位置的频道信息
     *
     * @param position 数据编号，从0开始
     * @return position对应的频道对象
     */
    public News getNews(int position) {
        return this.data.get(position);
    }

    /**
     * 访问网络得到真实数据，代替以前的test()方法
     */
    public void getData(Handler handler) {
        //调用单例
        Retrofit retrofit = RetrofitClient.getInstance();

        NewsApi api = retrofit.create(NewsApi.class);
        Call<List<News>> call = api.getAllNews();
        //enqueue会自己生成子线程， 去执行后续代码
        call.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call,
                                   Response<List<News>> response) {
                if (null != response && null != response.body()) {
                    Log.d("News", "从阿里云得到的数据是：");
                    Log.d("News", response.body().toString());
                    data = response.body();
                    //发出通知
                    Message msg = new Message();
                    msg.what = 1;  //自己规定1代表从阿里云获取数据完毕
                    handler.sendMessage(msg);
                } else {
                    Log.w("Game", "response没有数据！");
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                Log.e("News", "访问网络失败！", t);
            }
        });
    }
}
