package cn.edu.scujcc.youxizixun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

public class helloActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_welcome);
        Animation alpha = new AlphaAnimation(0.1f, 1.0f);
        alpha.setRepeatCount(0);
        alpha.setDuration(2000);
        View v = View.inflate(this, R.layout.activity_hello, null);
        setContentView(v);
        v.startAnimation(alpha);

        alpha.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                redirectTo();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void redirectTo(){
        Intent intent = new Intent(helloActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
