package cn.edu.scujcc.youxizixun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(v ->{
            Intent intent = new Intent(LoginActivity.this,youxikuActivity.class);
            startActivity(intent);
        });
    }
}
