package cn.edu.scujcc.youxizixun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.Gallery;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class youxiku extends AppCompatActivity {

    private RecyclerView gameRv;
    private GameRvAdapter rvAdapter;
    private GameLab lab = GameLab.getInstance();
    //线程通讯第1步，在主线程创建Handler
    private Handler handler = new Handler(){
        //快捷键ctrl o
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch(msg.what){
                case GameLab.MSG_CHANNELS:
                    rvAdapter.notifyDataSetChanged();
                    break;
                case GameLab.MSG_FAILURE:
                    break;
            }

        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youxiku);

        Button bangdanButton = findViewById(R.id.bangdanbutton);
        bangdanButton.setOnClickListener(v ->{
            Intent intent = new Intent(youxiku.this,BangdanActivity.class);
            startActivity(intent);
        });

        this.gameRv = findViewById(R.id.game_rv);
//        rvAdapter = new GameRvAdapter(youxiku.this, p -> {
//            //跳转到新界面。
//            Intent intent = new Intent(youxiku.this, BangdanActivity.class);
//            //TODO 传递用户选中的频道到下一个界面
//            //通过为止p得到当前频道channel?
//            Game c = lab.getGame(p);
//            intent.putExtra("game", c);
//            startActivity(intent);
//        });
        this.gameRv.setAdapter(rvAdapter);
        this.gameRv.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    protected void onResume() {
        super.onResume();
        //把主线程的handler传递给子线程使用
        lab.getData(handler);
    }



}
