package cn.edu.scujcc.youxizixun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.Serializable;

public class GamePlayActivity extends AppCompatActivity {
    private Game currentGame;
    private TextView gameTitle,gamePrice;
    private ImageView gameCover;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        Serializable s = getIntent().getSerializableExtra("game");
        Log.d("Game", "取得的当前游戏是： "+s);
        if(s!=null && s instanceof Game){
            currentGame = (Game) s;
            }
        updateUI();

        ImageButton gouwuche = findViewById(R.id.gouwuche_button);
        gouwuche.setOnClickListener(v ->{
            Toast.makeText(GamePlayActivity.this,
                    "已添加进愿望清单！",
                    Toast.LENGTH_LONG).show();
            Intent intent = new Intent(GamePlayActivity.this,youxikuActivity.class);
            startActivity(intent);
        });

        Gallery gallery = (Gallery) findViewById(R.id.gallery);
        gallery.setAdapter(new GameAdapter.ImageAdapter(this));   // gallery添加ImageAdapter图片资源
        }

    private void updateUI(){
        gameTitle = findViewById(R.id.game_title);
        gamePrice = findViewById(R.id.game_price);
        gameTitle.setText(currentGame.getTitle());
        gamePrice.setText(currentGame.getPrice());
    }



}
