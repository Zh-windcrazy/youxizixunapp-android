package cn.edu.scujcc.youxizixun;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserLab {
    public final static int USER_LOGIN_SUCCESS = 1;
    public final static int USER_LOGIN_PASSWORD_ERROR = -1;
    public final static int USER_LOGIN_NET_ERROR = -2;
    public final static int USER_REGISTER_FAIL = -3;
    public final static int USER_REGISTER_SUCCESS = 2;
    private final static String TAG = "Game";
    private static UserLab INSTANCE = null;

    private UserLab() {
    }

    public static UserLab getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new UserLab();
        }
        return INSTANCE;
    }

    public void login(String username, String password, Handler handler) {
        Retrofit retrofit = RetrofitClient.getInstance();
        UserApi api = retrofit.create(UserApi.class);
        Call<Integer> call = api.login(username, password);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Log.d(TAG, "登录成功返回数据");
                boolean loginSuccess = false;
                if (response.body() != null) {
                    int result = response.body().intValue();
                    if (result == 1) {  //登录成功
                        loginSuccess = true;
                    }
                }
                if (loginSuccess) {
                    Message msg = new Message();
                    msg.what = USER_LOGIN_SUCCESS;
                    handler.sendMessage(msg);
                } else {
                    Message msg = new Message();
                    msg.what = USER_LOGIN_PASSWORD_ERROR;
                    handler.sendMessage(msg);
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.e(TAG, "登录失败！", t);
                Message msg = new Message();
                msg.what = USER_LOGIN_NET_ERROR;
                handler.sendMessage(msg);
            }
        });
    }

    /**
     * 向服务器发送用户注册的请求。
     *
     * @param user 用户填写的信息
     */
    public void register(User user, Handler handler) {
        Retrofit retrofit = RetrofitClient.getInstance();
        UserApi api = retrofit.create(UserApi.class);
        Log.d(TAG, "准备注册的用户信息是：" + user);
        Call<User> call = api.register(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d(TAG, "用户成功注册!");
                Log.d(TAG, "返回数据是：" + response.body());
                Message msg = new Message();
                msg.what = USER_REGISTER_SUCCESS;
                handler.sendMessage(msg);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "注册失败", t);
                Message msg = new Message();
                msg.what = USER_REGISTER_FAIL;
                handler.sendMessage(msg);
            }
        });
    }
}
