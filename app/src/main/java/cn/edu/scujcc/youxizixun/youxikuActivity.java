package cn.edu.scujcc.youxizixun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class youxikuActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private RecyclerView gameRv;
    private GameRvAdapter rvAdapter;
    private GameLab lab = GameLab.getInstance();
    //线程通讯第1步，在主线程创建Handler
    private Handler handler = new Handler() {
        //快捷键ctrl o
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
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

        ImageButton qiehuan2 = findViewById(R.id.shouyeButton);
        qiehuan2.setOnClickListener(v ->{
            Intent intent = new Intent(youxikuActivity.this,MainActivity.class);
            startActivity(intent);
        });


        Button bangdanButton = findViewById(R.id.bangdanbutton);
        bangdanButton.setOnClickListener(v -> {
            Intent intent = new Intent(youxikuActivity.this, BangdanActivity.class);
            startActivity(intent);
        });

        this.gameRv = findViewById(R.id.game_rv);
        rvAdapter = new GameRvAdapter(youxikuActivity.this, p -> {
            //跳转到新界面。
            Intent intent = new Intent(youxikuActivity.this, GamePlayActivity.class);
            Game c = lab.getGame(p);
            intent.putExtra("game", c);
            startActivity(intent);
        });
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
