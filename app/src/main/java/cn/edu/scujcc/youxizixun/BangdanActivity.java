package cn.edu.scujcc.youxizixun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class BangdanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bangdan);
        Button youxikuButton = findViewById(R.id.youxikubutton);
        youxikuButton.setOnClickListener(v ->{
            Intent intent = new Intent(BangdanActivity.this,youxiku.class);
            startActivity(intent);
        });
    }
}
