package cn.edu.scujcc.youxizixun;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.Serializable;

public class NewsPlayActivity extends AppCompatActivity {
    private News currentNews;
    private TextView newsTitle,newsContent,newsUname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_play);

        Serializable s = getIntent().getSerializableExtra("news");
        Log.d("News", "取得的当前新闻是： "+s);
        if(s!=null && s instanceof News){
            currentNews = (News) s;
        }
        updateUI();
    }

    private void updateUI(){
        newsTitle = findViewById(R.id.news_title);
        newsContent = findViewById(R.id.news_content);
        newsUname = findViewById(R.id.news_uname);
        newsTitle.setText(currentNews.getTitle());
        newsContent.setText(currentNews.getContent());
        newsUname.setText(currentNews.getUname());
    }
}
