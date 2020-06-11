package cn.edu.scujcc.youxizixun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    private RecyclerView newsRv;
    private NewsRvAdapter rvAdapter;
    private NewsLab lab = NewsLab.getInstance();

    //线程通讯第1步，在主线程创建Handler
    private Handler handler = new Handler() {
        //按快捷键Ctrl o
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 1) {
                rvAdapter.notifyDataSetChanged();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton qiehuan1 = findViewById(R.id.youxikuButton);
        qiehuan1.setOnClickListener(v ->{
            Intent intent = new Intent(MainActivity.this,youxikuActivity.class);
            startActivity(intent);
        });

        this.newsRv = findViewById(R.id.news_rv);
        //lambda简化
        //适应handler，把适配器改为实例变量
        rvAdapter = new NewsRvAdapter(MainActivity.this, p -> {
            //跳转到新界面，使用意图Intent
            Intent intent = new Intent(MainActivity.this, NewsPlayActivity.class);
            //通过位置p得到当前频道channel，传递用户选中的频道到下一个界面
            News c = lab.getNews(p);
            intent.putExtra("news", c);
            startActivity(intent);
        });

        this.newsRv.setAdapter(rvAdapter);
        this.newsRv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        //把主线程的handler传递给子线程使用
        lab.getData(handler);
    }
}
