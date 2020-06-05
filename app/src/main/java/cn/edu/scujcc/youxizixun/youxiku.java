package cn.edu.scujcc.youxizixun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Gallery;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class youxiku extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youxiku);
        Button bangdanButton = findViewById(R.id.bangdanbutton);
        bangdanButton.setOnClickListener(v ->{
            Intent intent = new Intent(youxiku.this,BangdanActivity.class);
            startActivity(intent);
        });
    }



}
